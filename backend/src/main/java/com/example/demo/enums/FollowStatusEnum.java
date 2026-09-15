package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FollowStatusEnum {
    FOLLOWED(0, "已关注"),
    BLOCKED(1, "已拉黑");


    private final int code;
    private final String desc;
}
