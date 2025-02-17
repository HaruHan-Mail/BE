package com.haruhan.bookmark.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhan.bookmark.dto.BookmarkReqDto;
import com.haruhan.bookmark.service.BookmarkService;
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

@WebMvcTest(BookmarkController.class)
@AutoConfigureMockMvc
public class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc; // 가짜 http 요청을 보낼 수 있도록 설정

    @Autowired
    private ObjectMapper objectMapper; // Json 변환을 위한 ObjectMapper

    @MockBean
    private BookmarkService bookmarkService; // 가짜(Mock) 서비스 사용

    @Test
    @DisplayName("정상적인 찜하기 요청을 받았을때 HTTP 200 응답 확인하기")
    void 올바른_요청일때_200확인() throws Exception {
        //Given
        BookmarkReqDto validDto = new BookmarkReqDto("test4@example.com",1L);
        doNothing().when(bookmarkService).addBookmark(any(BookmarkReqDto.class));

        //When & Then
        mockMvc.perform(post("/bookmark")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(status().isOk());

        verify(bookmarkService, times(1)).addBookmark(any(BookmarkReqDto.class));
    }
}
