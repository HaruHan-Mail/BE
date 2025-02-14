package com.haruhan.feedback.service;

import com.haruhan.feedback.dto.FeedbackPostRequestDto;

public interface FeedbackService {
    void saveFeedback(FeedbackPostRequestDto feedbackPostRequestDto);

}
