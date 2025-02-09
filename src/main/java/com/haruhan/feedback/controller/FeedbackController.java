package com.haruhan.feedback.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.feedback.dto.PostFeedbackDto;
import com.haruhan.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Message> receiveFeedback(@Valid @RequestBody PostFeedbackDto postFeedbackDto) {
        feedbackService.saveFeedback(postFeedbackDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}

