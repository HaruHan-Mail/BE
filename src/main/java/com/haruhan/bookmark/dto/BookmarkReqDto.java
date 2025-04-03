package com.haruhan.bookmark.dto;

public record BookmarkReqDto(
        String email,
        String token,
        Long contentId
) {}

