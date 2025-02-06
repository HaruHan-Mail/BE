package com.haruhan.user.dto;

import com.haruhan.user.entity.PreferedTime;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    private PreferedTime preferedTime;
    private Boolean isDaily; //true면 매일 하나씩, false면 월요일에 5개
}