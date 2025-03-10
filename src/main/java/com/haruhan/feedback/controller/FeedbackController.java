package com.haruhan.feedback.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.feedback.dto.FeedbackPostRequestDto;
import com.haruhan.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Message> receiveFeedback(@Valid @RequestBody FeedbackPostRequestDto feedbackPostRequestDto) {
        feedbackService.saveFeedback(feedbackPostRequestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}

