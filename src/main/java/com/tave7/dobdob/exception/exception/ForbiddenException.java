package com.tave7.dobdob.exception.exception;

import com.tave7.dobdob.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ForbiddenException extends RuntimeException {
    // 예외 세팅
    public static final ForbiddenException NOT_AGREE_SOCIAL_LOGIN = new ForbiddenException(ErrorCode.NOT_AGREE_SOCIAL_LOGIN);

    public final ErrorCode errorCode;
}
