package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    // 通用错误
    SYSTEM_ERROR(100, "系统错误"),
    PARAM_INVALID(101, "参数校验失败"),
    UNAUTHORIZED(103, "未登录或登录已过期"),
    FORBIDDEN(104, "无权限访问"),
    NOT_FOUND(105, "资源不存在"),

    // 用户模块错误
    USER_NOT_FOUND(201, "用户不存在"),
    USER_DISABLED(202, "用户已被禁用"),
    USER_EXIST(203, "用户已存在"),

    ;


    private final int code;
    private final String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
