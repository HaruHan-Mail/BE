package com.haruhan.feedback.service;

import com.haruhan.feedback.dto.PostFeedbackDto;

public interface FeedbackService {
    void saveFeedback(PostFeedbackDto postFeedbackDto);

}
