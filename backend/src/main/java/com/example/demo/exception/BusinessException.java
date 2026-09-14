package com.example.demo.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final String errorType;

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.errorType = errorEnum.name();
    }

    public BusinessException(ErrorEnum errorEnum, String message) {
        super(message);
        this.code = errorEnum.getCode();
        this.errorType = errorEnum.name();
    }

    public BusinessException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getMessage(), cause);
        this.code = errorEnum.getCode();
        this.errorType = errorEnum.name();
    }

    public BusinessException(ErrorEnum errorEnum, String message, Throwable cause) {
        super(message, cause);
        this.code = errorEnum.getCode();
        this.errorType = errorEnum.name();
    }
}