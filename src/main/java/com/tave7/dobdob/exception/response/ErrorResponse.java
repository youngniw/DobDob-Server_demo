package com.tave7.dobdob.exception.response;

import com.tave7.dobdob.exception.code.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final Integer errorId;
    private final HttpStatus errorStatus;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorId = errorCode.getId();
        this.errorStatus = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}
