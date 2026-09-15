package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果
 *
 * @param <T> 业务数据类型
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    /** 状态码 */
    private int code;

    /** 业务信息 */
    private String message;

    /** 返回业务数据 */
    private T data;

    /** 成功，无数据 */
    public static <T> Result<T> ok() {
        return Result.<T>builder()
                .code(200)
                .message("操作成功")
                .build();
    }

    /** 成功，带数据 */
    public static <T> Result<T> ok(T data) {
        return Result.<T>builder()
                .code(200)
                .message("操作成功")
                .data(data)
                .build();
    }

    /** 成功，自定义消息 + 数据 */
    public static <T> Result<T> ok(String message, T data) {
        return Result.<T>builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    /** 失败 */
    public static <T> Result<T> error(int code, String message) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Result<T> error(int code, String message, T data) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}