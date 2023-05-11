package com.tave7.dobdob.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ALL_INTERNAL_EXCEPTION(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    NOT_VALID_ARGUMENT_EXCEPTION(400, HttpStatus.BAD_REQUEST, "요청 정보가 유효하지 않습니다."),
    NOT_VALID_SOCIAL_LOGIN_TOKEN(4001, HttpStatus.BAD_REQUEST, "소셜 로그인 토큰이 유효하지 않습니다."),
    INVALID_JWT_TOKEN(4011, HttpStatus.UNAUTHORIZED, "유효하지 않은 jwt 토큰입니다."),
    EXPIRED_JWT_TOKEN(4012, HttpStatus.UNAUTHORIZED, "만료된 jwt 토큰입니다."),
    NOT_AGREE_SOCIAL_LOGIN(4031, HttpStatus.BAD_REQUEST, "카카오 로그인 시, 이메일 동의 항목에 사용자 동의 필요합니다.");

    private final Integer id;
    private final HttpStatus status;
    private final String message;
}
