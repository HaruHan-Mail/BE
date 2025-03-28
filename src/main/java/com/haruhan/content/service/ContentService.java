package com.haruhan.content.service;

import com.haruhan.content.dto.ContentResDto;

import java.util.List;

public interface ContentService {

    List<ContentResDto> getUserReceivedContent(String email);
    List<ContentResDto> getTop5BookmarkedContent();
}
