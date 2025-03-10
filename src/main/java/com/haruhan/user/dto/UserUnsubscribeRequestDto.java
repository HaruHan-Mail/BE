package com.haruhan.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserUnsubscribeRequestDto(
        @NotNull String email,
        @NotNull String token
) {
}
