package com.haruhan.bookmark.dto;

import java.util.List;

public record BookmarkGetResDto(
        Long contentId,
        String title,
        String summary,
        List<String> background,
        List<String> importance,
        List<String> tip,
        List<String> additionalResources
) {}
