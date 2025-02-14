package com.haruhan.user.dto;

import com.haruhan.user.entity.PreferedTime;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank(message = "이메일을 입력하세요.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email,

        @NotNull
        PreferedTime preferedTime,

        @NotNull
        Boolean isDaily
) {}