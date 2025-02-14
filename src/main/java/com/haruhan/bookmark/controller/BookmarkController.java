package com.haruhan.bookmark.controller;

import com.haruhan.bookmark.dto.BookmarkGetResDto;
import com.haruhan.bookmark.dto.BookmarkReqDto;
import com.haruhan.bookmark.service.BookmarkService;
import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
@CrossOrigin
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<Message> addBookmark(@RequestBody BookmarkReqDto bookmarkReqDto) {
        bookmarkService.addBookmark(bookmarkReqDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteBookmark(@RequestBody BookmarkReqDto bookmarkReqDto) {
        bookmarkService.deleteBookmark(bookmarkReqDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<Message> getAllBookmarks(@PathVariable String userEmail) {
        return ResponseEntity.ok(new Message(StatusCode.OK, bookmarkService.getBookmarkContent(userEmail)));
    }
}
