package com.haruhan.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PreferedTime {
    MORNING("오전 7시"),
    AFTERNOON("오후 12시"),
    EVENING("오후 6시");

    private final String displayName;

    @JsonCreator
    public static PreferedTime fromString(String value) {
        return Arrays.stream(PreferedTime.values())
                .filter(preferedTime -> preferedTime.displayName.equals(value))
                .findFirst()
                .orElseThrow(() -> new CustomException(StatusCode.INVALID_INPUT));
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
//클라이언트가 세 가지 옵션 중 하나를 선택하면 해당 시간에 이메일을 받을 수 있는 기능을 구현할 구조o
//저장된 preferedTime을 기반으로 이메일을 해당 시간에 전송하는 로직 필요
//이메일 전송 기능이 추가된 후, 이와 연관지어 사용자가 선택한 시간에 전송하면 됨