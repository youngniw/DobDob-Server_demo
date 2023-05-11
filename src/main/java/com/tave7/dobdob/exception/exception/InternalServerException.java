package com.tave7.dobdob.exception.exception;

import com.tave7.dobdob.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InternalServerException extends RuntimeException {
	public static final InternalServerException ALL_INTERNAL_EXCEPTION = new InternalServerException(ErrorCode.ALL_INTERNAL_EXCEPTION);

	private final ErrorCode errorCode;
}
