package com.haruhan.bookmark.service;

import com.haruhan.bookmark.dto.BookmarkGetResDto;
import com.haruhan.bookmark.dto.BookmarkReqDto;
import com.haruhan.bookmark.entity.Bookmark;
import com.haruhan.bookmark.entity.BookmarkId;
import com.haruhan.bookmark.repository.BookmarkRepository;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.entity.Content;
import com.haruhan.common.error.repository.ContentRepository;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    // 사용 가능한 데이터인지 확인하는 메소드
    private Bookmark isUsableData(BookmarkReqDto bookmarkReqDto) {
        // 이메일로 사용자 찾기
        User user = userRepository.findByEmail(bookmarkReqDto.userEmail())
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));

        // 컨텐츠 찾기
        Content content = contentRepository.findById(bookmarkReqDto.contentId())
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND));

        return new Bookmark(user, content);
    }

    @Override
    public void addBookmark(BookmarkReqDto bookmarkReqDto) {

        Bookmark bookmark = isUsableData(bookmarkReqDto);
        BookmarkId bookmarkId = bookmark.getBookmarkId();

        // 중복된 북마크인지 확인
        if (bookmarkRepository.existsByBookmarkId(bookmarkId)) {
            throw new CustomException(StatusCode.ALREADY_BOOKMARKED);
        }

        // 북마크 저장
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteBookmark(BookmarkReqDto bookmarkReqDto) {

        Bookmark bookmark = isUsableData(bookmarkReqDto);

        // 북마크 삭제
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public List<BookmarkGetResDto> getBookmarkContent(String userEmail) {
        // 이메일로 사용자 찾기
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));

        // 사용자의 찜한 지식 목록 가져오기
        List<Bookmark> bookmarks = bookmarkRepository.findByUser(user);

        // Bookmark를 DTO로 변환 후 반환
        return bookmarks.stream()
                .map(bookmark -> new BookmarkGetResDto(
                        bookmark.getContent().getContent_id(),
                        bookmark.getContent().getTitle(),
                        bookmark.getContent().getSummary(),
                        bookmark.getContent().getBackground(),
                        bookmark.getContent().getImportance(),
                        bookmark.getContent().getTip(),
                        bookmark.getContent().getAdditional_resources()
                )).collect(Collectors.toList());
    }
}
