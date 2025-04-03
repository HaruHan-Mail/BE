package com.haruhan.content.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.content.dto.ContentReqDto;
import com.haruhan.content.dto.ContentResDto;
import com.haruhan.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@CrossOrigin
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/mine/{email}")
    public ResponseEntity<Message> getUserReceivedContent(@PathVariable String email) {
        List<ContentResDto> contentList = contentService.getUserReceivedContent(email);
        return ResponseEntity.ok(new Message(StatusCode.OK, contentList));
    }

    @GetMapping("/top5")
    public ResponseEntity<Message> getTop5BookmarkedContent() {
        List<ContentResDto> top5Content = contentService.getTop5BookmarkedContent();
        return ResponseEntity.ok(new Message(StatusCode.OK, top5Content));
    }

    @PostMapping
    public ResponseEntity<Message> createContent(@RequestBody ContentReqDto dto) {
        ContentResDto content = contentService.createContent(dto);
        return ResponseEntity.ok(new Message(StatusCode.CREATED, content));
    }
}
