package com.haruhan.feedback.service;

import com.haruhan.feedback.dto.FeedbackGetResDto;
import com.haruhan.feedback.dto.FeedbackPostRequestDto;

import java.util.List;

public interface FeedbackService {
    //피드백 입력받아 저장하기
    void saveFeedback(FeedbackPostRequestDto feedbackPostRequestDto);
    //모든 피드백 조회하기
    List<FeedbackGetResDto> getFeedback();
}
