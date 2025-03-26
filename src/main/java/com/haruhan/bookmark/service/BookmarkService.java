package com.haruhan.bookmark.service;

import com.haruhan.bookmark.dto.BookmarkGetReqDto;
import com.haruhan.bookmark.dto.BookmarkGetResDto;
import com.haruhan.bookmark.dto.BookmarkReqDto;

import java.util.List;

public interface BookmarkService {
    //마음에 드는 컨텐츠 찜하기
    void addBookmark(BookmarkReqDto bookmarkReqDto);
    //찜했던 컨텐츠 해제하기
    void deleteBookmark(BookmarkReqDto bookmarkReqDto);
    //내가 찜한 컨텐츠 조회하기
    List<BookmarkGetResDto> getBookmarkContent(BookmarkGetReqDto bookmarkGetReqDto);
    //내가 이미 찜한 컨텐츠인지 확인하기
    boolean isBookmarked(BookmarkReqDto bookmarkReqDto);
}
