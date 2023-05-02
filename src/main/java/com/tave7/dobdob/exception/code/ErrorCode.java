package com.tave7.dobdob.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ALL_INTERNAL_EXCEPTION(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    NOT_VALID_ARGUMENT_EXCEPTION(400, HttpStatus.BAD_REQUEST, "요청 정보가 유효하지 않습니다.");

    private final Integer id;
    private final HttpStatus status;
    private final String message;
}
