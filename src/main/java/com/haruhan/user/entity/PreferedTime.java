package com.haruhan.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
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
                .orElse(null); // 예외를 던지는 대신 null 반환
    }
}