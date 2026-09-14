package com.example.demo.exception;

import com.example.demo.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    // 错误码
    private final int errorCode;

    // http状态码
    private final int httpStatus;

    // 错误类型
    private final String errorType;

    public AppException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorCode = errorEnum.getErrorCode();
        this.httpStatus = errorEnum.getHttpStatus();
        this.errorType = errorEnum.name();
    }

    public AppException(ErrorEnum errorEnum, String message) {
        super(message);
        this.errorCode = errorEnum.getErrorCode();
        this.httpStatus = errorEnum.getHttpStatus();
        this.errorType = errorEnum.name();
    }

    public AppException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getMessage(), cause);
        this.errorCode = errorEnum.getErrorCode();
        this.httpStatus = errorEnum.getHttpStatus();
        this.errorType = errorEnum.name();
    }

    public AppException(ErrorEnum errorEnum, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorEnum.getErrorCode();
        this.httpStatus = errorEnum.getHttpStatus();
        this.errorType = errorEnum.name();
    }
}