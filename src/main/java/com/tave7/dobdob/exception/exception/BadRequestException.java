package com.tave7.dobdob.exception.exception;

import com.tave7.dobdob.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BadRequestException extends RuntimeException {
    // 예외 세팅

    public final ErrorCode errorCode;
}
