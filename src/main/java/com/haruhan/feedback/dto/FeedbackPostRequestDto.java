package com.haruhan.feedback.dto;

import jakarta.validation.constraints.NotNull;

public record FeedbackPostRequestDto(
        @NotNull(message = "피드백 내용은 필수입니다.")
        String feedback_content
) {}
