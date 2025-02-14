package com.haruhan.feedback.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.feedback.dto.PostFeedbackDto;
import com.haruhan.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Message> receiveFeedback(@Valid @RequestBody PostFeedbackDto postFeedbackDto) {
        feedbackService.saveFeedback(postFeedbackDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}

