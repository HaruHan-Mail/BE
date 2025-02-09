package com.haruhan.feedback.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.feedback.dto.PostFeedbackDto;
import com.haruhan.feedback.entity.Feedback;
import com.haruhan.feedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;

    @Override
    public void saveFeedback(PostFeedbackDto postFeedbackDto) {
        if(postFeedbackDto.feedback_content() == null || postFeedbackDto.feedback_content().isEmpty()){
            throw new CustomException(StatusCode.NOT_EXIST);
        }
        Feedback feedback = new Feedback(postFeedbackDto.feedback_content());
        feedbackRepository.save(feedback);
    }
}
