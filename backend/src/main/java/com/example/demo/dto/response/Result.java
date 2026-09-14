package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    // 状态码
    private int code;

    // 业务信息
    private String message;

    // 返回业务数据
    private Object data;

    public static Result ok(Object data) {
        return Result.builder().code(200).message("操作成功").data(data).build();
    }

    public static Result ok(String message, Object data) {
        return Result.builder().code(200).message(message).data(data).build();
    }

    public static Result error(int code, String message) {
        return Result.builder().code(code).message(message).build();
    }
}