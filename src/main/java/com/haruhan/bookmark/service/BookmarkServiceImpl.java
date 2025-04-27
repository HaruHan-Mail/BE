package com.haruhan.bookmark.service;

import com.haruhan.bookmark.dto.BookmarkGetResDto;
import com.haruhan.bookmark.dto.BookmarkReqDto;
import com.haruhan.bookmark.entity.Bookmark;
import com.haruhan.bookmark.entity.BookmarkId;
import com.haruhan.bookmark.repository.BookmarkRepository;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.content.entity.Content;
import com.haruhan.content.repository.ContentRepository;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        // 토큰 값으로 사용자 찾기
        User user = userRepository.findByToken(bookmarkReqDto.token())
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

        Content content = bookmark.getContent();
        // 북마크 카운트 증가
        content.increaseBookmarkCount();
        contentRepository.save(content);
        // 북마크 저장
        bookmarkRepository.save(bookmark);

    }

    @Override
    public void deleteBookmark(BookmarkReqDto bookmarkReqDto) {

        Bookmark bookmark = isUsableData(bookmarkReqDto);
        Content content = bookmark.getContent();
        // 북마크 카운트 감소
        content.decreaseBookmarkCount();

        // 북마크 삭제
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public List<BookmarkGetResDto> getBookmarkContent(String email, String token) {
        // 토큰으로 사용자 찾기
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));

        if (!user.getEmail().equals(email)) {
            throw new CustomException(StatusCode.INVALID_INPUT);
        }

        // 사용자의 북마크 목록 조회
        List<Bookmark> bookmarks = bookmarkRepository.findByUserFetchContent(user);

        // Bookmark를 DTO로 변환 후 반환
        return bookmarks.stream()
                .map(bookmark -> new BookmarkGetResDto(
                        bookmark.getContent().getContentId(),
                        bookmark.getContent().getTitle(),
                        bookmark.getContent().getSummary(),
                        splitByNewLine(bookmark.getContent().getBackground()),
                        splitByNewLine(bookmark.getContent().getImportance()),
                        splitByNewLine(bookmark.getContent().getTip()),
                        splitByNewLine(bookmark.getContent().getAdditionalResources())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBookmarked(BookmarkReqDto bookmarkReqDto) {
        Bookmark bookmark = isUsableData(bookmarkReqDto);
        BookmarkId bookmarkId = bookmark.getBookmarkId();

        // 북마크한 지식인지 확인
        if (bookmarkRepository.existsByBookmarkId(bookmarkId)) {
            return true;
        }
        return false;
    }

    //문자열을 줄바꿈 기준으로 분리하여 리스트로 변환
    private List<String> splitByNewLine(String text) {
        return Arrays.stream(text.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
