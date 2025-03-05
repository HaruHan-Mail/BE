package com.haruhan.user.dto;

import com.haruhan.user.entity.PreferedTime;

public record UserConfirmRequestDto(
        String email,
        PreferedTime preferedTime,
        Boolean isDaily,
        String verificationCode
) {}

