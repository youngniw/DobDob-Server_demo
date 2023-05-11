package com.tave7.dobdob.exception.exception;

import com.tave7.dobdob.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnAuthorizedException extends RuntimeException {
    public static final UnAuthorizedException INVALID_JWT_TOKEN= new UnAuthorizedException(ErrorCode.INVALID_JWT_TOKEN);
    public static final UnAuthorizedException EXPIRED_JWT_TOKEN= new UnAuthorizedException(ErrorCode.EXPIRED_JWT_TOKEN);

    public final ErrorCode errorCode;
}
