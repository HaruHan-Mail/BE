package com.haruhan.content.dto;

import java.util.List;

public record ContentReqDto(
        String title,
        String summary,
        List<String> background,
        List<String> importance,
        List<String> tip,
        List<String> additionalResources
) {}