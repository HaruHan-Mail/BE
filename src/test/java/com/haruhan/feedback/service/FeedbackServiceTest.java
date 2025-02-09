package com.haruhan.feedback.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.feedback.dto.PostFeedbackDto;
import com.haruhan.feedback.entity.Feedback;
import com.haruhan.feedback.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("피드백이 정상적으로 저장되는지 확인")
    void 피드백저장확인() {
        //Given
        PostFeedbackDto validDto = new PostFeedbackDto("valid feedback content");

        //When
        feedbackService.saveFeedback(validDto);

        //Then
        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    @DisplayName("Null 값인 피드백이 들어왔을때 예외처리 확인")
    void NULL피드백예외처리확인() {
        //Given
        PostFeedbackDto invalidDto = new PostFeedbackDto(null);

        //When
        CustomException exception = assertThrows(CustomException.class, () -> feedbackService.saveFeedback(invalidDto));

        //Then
        assertEquals(StatusCode.NOT_EXIST, exception.getStatusCode());
    }

    @Test
    @DisplayName("빈 값인 피드백이 들어왔을때 예외처리 확인")
    void Empty피드백예외처리확인() {
        //Given
        PostFeedbackDto invalidDto = new PostFeedbackDto("");

        //When
        CustomException exception = assertThrows(CustomException.class, () -> feedbackService.saveFeedback(invalidDto));

        //Then
        assertEquals(StatusCode.NOT_EXIST, exception.getStatusCode());
    }
}
