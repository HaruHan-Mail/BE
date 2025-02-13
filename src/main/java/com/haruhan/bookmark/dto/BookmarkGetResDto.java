package com.haruhan.bookmark.dto;

public record BookmarkGetResDto(
        Long contentId,
        String title,
        String summary,
        String background,
        String importance,
        String tip,
        String additionalResources
) {}
