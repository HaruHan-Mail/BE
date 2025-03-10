package com.haruhan.bookmark.dto;

public record BookmarkReqDto(
        String userEmail,
        String token,
        Long contentId
) {}
