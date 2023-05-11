package com.tave7.dobdob.exception.handler;

import com.tave7.dobdob.exception.code.ErrorCode;
import com.tave7.dobdob.exception.exception.BadRequestException;
import com.tave7.dobdob.exception.exception.ForbiddenException;
import com.tave7.dobdob.exception.exception.InternalServerException;
import com.tave7.dobdob.exception.exception.UnAuthorizedException;
import com.tave7.dobdob.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ResponseExceptionHandler {
    // 전체 예외 관련 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class })
    protected ErrorResponse handleAllException(Exception e) {
        log.warn(e.getMessage());

        return new ErrorResponse(ErrorCode.ALL_INTERNAL_EXCEPTION);
    }

    // 500 오류 관련 예외 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { InternalServerException.class })
    protected ErrorResponse internalServerException(InternalServerException e) {
        log.warn(e.getMessage());

        return new ErrorResponse(ErrorCode.ALL_INTERNAL_EXCEPTION);
    }

    // 4xx 오류 관련 예외 처리 (400)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    protected ErrorResponse badRequestException(BadRequestException e) {
        log.warn(e.getMessage());

        return new ErrorResponse(e.getErrorCode());
    }

    // @Valid에 따른 메서드 파라미터 관련 예외 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ErrorResponse badRequestExceptionValid(MethodArgumentNotValidException e) {
        if (e.getBindingResult().hasErrors()) {
            // @Valid 사용 메서드 파라미터 관련 오류가 있는 경우
            // TODO: 작성
        }

        return new ErrorResponse(ErrorCode.NOT_VALID_ARGUMENT_EXCEPTION);
    }

    // 401 오류 관련 예외 처리
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnAuthorizedException.class)
    protected ErrorResponse unAuthorizedException(UnAuthorizedException e) {
        log.warn(e.getMessage());

        return new ErrorResponse(e.getErrorCode());
    }

    // 403 오류 관련 예외 처리
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = ForbiddenException.class)
    protected ErrorResponse forbiddenException(ForbiddenException e) {
        log.warn(e.getMessage());

        return new ErrorResponse(e.getErrorCode());
    }
}
