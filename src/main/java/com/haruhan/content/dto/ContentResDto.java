package com.haruhan.content.dto;

//사용자가 받은 컨텐츠 정보를 응답하는 DTO
public record ContentResDto(
        Long id,
        String title,
        String summary,
        String background,
        String importance,
        String tip,
        String additionalResources
) {}