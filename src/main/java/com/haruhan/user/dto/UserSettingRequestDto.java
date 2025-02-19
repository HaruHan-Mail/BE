package com.haruhan.user.dto;

import com.haruhan.user.entity.PreferedTime;
import jakarta.validation.constraints.NotNull;

public record UserSettingRequestDto (
        @NotNull String email,
        @NotNull PreferedTime preferedTime,
        @NotNull boolean isDaily

) {}