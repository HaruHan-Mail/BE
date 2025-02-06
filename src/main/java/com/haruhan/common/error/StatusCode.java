package com.haruhan.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {
    OK(200, "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    ALREADY_EXIST(400, "이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST),
    NOT_EXIST(400, "데이터가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND(404, "존재하지 않는 데이터입니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int statusCode;
    private final String message;
    private final HttpStatus status;

    StatusCode(int statusCode, String message, HttpStatus status) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
    }
}

