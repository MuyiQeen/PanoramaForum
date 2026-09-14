package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    SYSTEM_ERROR(100, 500, "系统错误"),
    PARAM_INVALID(101, 400, "参数校验失败"),
    UNAUTHORIZED(102, 401, "未登录或登录已过期"),
    FORBIDDEN(103, 403, "无权限访问"),
    NOT_FOUND(104, 404, "资源不存在"),
    METHOD_NOT_ALLOWED(105, 405, "请求方法不支持"),

    USER_NOT_FOUND(200, 404, "用户不存在"),
    USER_DISABLED(201, 403, "用户已被禁用"),
    USER_EXIST(202, 409, "用户已存在"),

    ;

    // 业错误码
    private final int errorCode;

    // 状态码
    private final int httpStatus;

    // 业务错误信息
    private final String message;
}