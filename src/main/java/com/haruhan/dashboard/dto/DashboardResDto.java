package com.haruhan.dashboard.dto;

import org.springframework.data.domain.Page;

public record DashboardResDto(
        Page<DashboardUserDto> users,
        Page<DashboardContentDto> contents
) {
}
