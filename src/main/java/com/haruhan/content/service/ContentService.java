package com.haruhan.content.service;

import com.haruhan.content.dto.ContentReqDto;
import com.haruhan.content.dto.ContentResDto;

import java.util.List;

public interface ContentService {

    List<ContentResDto> getUserReceivedContent(String email, String token);

    List<ContentResDto> getTop5BookmarkedContent();

    ContentResDto getContent(Long contentId);

    ContentResDto createContent(ContentReqDto dto);

    ContentResDto updateContent(Long contentId, ContentReqDto dto);

    List<ContentResDto> getAllContents();
}