package com.haruhan.dashboard.dto;

import java.util.List;

public record DashboardResDto (
        List<DashboardUserDto> users,
        List<DashboardContentDto> contents
) {}
