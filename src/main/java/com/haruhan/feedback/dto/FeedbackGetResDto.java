package com.haruhan.feedback.dto;

import java.time.LocalDateTime;

public record FeedbackGetResDto(
        Long feedbackId,
        String feedbackContent,
        LocalDateTime createdAt
) {}
