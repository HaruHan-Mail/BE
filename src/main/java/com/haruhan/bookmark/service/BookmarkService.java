package com.haruhan.bookmark.service;

import com.haruhan.bookmark.dto.BookmarkGetReqDto;
import com.haruhan.bookmark.dto.BookmarkGetResDto;
import com.haruhan.bookmark.dto.BookmarkReqDto;

import java.util.List;

public interface BookmarkService {
    void addBookmark(BookmarkReqDto bookmarkReqDto);
    void deleteBookmark(BookmarkReqDto bookmarkReqDto);
    List<BookmarkGetResDto> getBookmarkContent(BookmarkGetReqDto bookmarkGetReqDto);
    boolean isBookmarked(BookmarkReqDto bookmarkReqDto);
}
