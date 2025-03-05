package com.haruhan.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {
    OK(200, "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    ALREADY_EXIST(409, "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
    NOT_EXIST(400, "데이터가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND(404, "존재하지 않는 데이터입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_USER(404, "존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    INVALID_INPUT(400, "잘못된 입력 값입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_BOOKMARKED(409, "이미 찜한 컨텐츠입니다.", HttpStatus.CONFLICT),
    INVALID_VERIFICATION_CODE(400, "인증번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
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

