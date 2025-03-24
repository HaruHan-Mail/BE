package com.haruhan.content.dto;

import java.util.List;

//사용자가 받은 컨텐츠 정보를 응답하는 DTO
public record ContentResDto(
        Long id,
        String title,
        String summary,
        List<String> background,
        List<String> importance,
        List<String> tip,
        List<String> additionalResources
) {}