package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MAN(1, "男"),
    WOMAN(0, "女"),
    UNKNOWN(2, "未知");

    @EnumValue  //返回数据库
    @JsonValue  //返回前端

    private final int code;
    private final String desc;
}
