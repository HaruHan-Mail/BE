package com.haruhan.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.entity.PreferedTime;
import com.haruhan.user.service.UserService;
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

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("올바른 구독 요청이면 HTTP 200 응답을 받아야 한다.")
    void 올바른_구독_요청_200확인() throws Exception {
        // Given
        UserRequestDto validDto = new UserRequestDto("test@example.com", PreferedTime.MORNING, true);
        doNothing().when(userService).subscribe(any(UserRequestDto.class));

        // When & Then
        mockMvc.perform(post("/user/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).subscribe(any(UserRequestDto.class));
    }

    @Test
    @DisplayName("이미 구독된 이메일이면 409 Conflict 응답을 받아야 한다.")
    void 이미_구독된_이메일_409_확인() throws Exception {
        // Given
        UserRequestDto duplicateDto = new UserRequestDto("test@example.com", PreferedTime.EVENING, false);
        doThrow(new CustomException(StatusCode.ALREADY_EXIST))
                .when(userService).subscribe(any(UserRequestDto.class));

        // When & Then
        mockMvc.perform(post("/user/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateDto)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).subscribe(any(UserRequestDto.class));
    }
}