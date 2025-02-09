package com.haruhan.feedback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhan.feedback.dto.PostFeedbackDto;
import com.haruhan.feedback.service.FeedbackService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class) // FeedbackController 만 테스트
@AutoConfigureMockMvc
public class FeedbackControllerTest {
    @Autowired
    private MockMvc mockMvc; // 가짜 http 요청을 보낼 수 있도록 설정

    @Autowired
    private ObjectMapper objectMapper; // Json 변환을 위한 ObjectMapper

    @MockBean
    private FeedbackService feedbackService; // 가짜(Mock) 서비스 사용

    @Test
    @DisplayName("올바른 피드백을 입력하면 HTTP 200 응답을 받아야 한다.")
    void 올바른_요청일때_200확인() throws Exception {
        // Given
        PostFeedbackDto validDto = new PostFeedbackDto("example content");
        doNothing().when(feedbackService).saveFeedback(any(PostFeedbackDto.class));

        // When & Then
        mockMvc.perform(post("/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(status().isOk()); // HTTP 200 응답 확인

        verify(feedbackService, times(1)).saveFeedback(any(PostFeedbackDto.class)); //
    }
}
