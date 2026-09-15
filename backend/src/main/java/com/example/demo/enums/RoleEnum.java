package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    SUPER_ADMIN(10, "超级管理员"),
    ADMIN(11, "管理员"),
    USER(12, "普通用户");

    private final int code;
    private final String roleName;

    /**
     * 根据 code 查枚举（数据库反查）
     *
     * @param code 角色 code
     * @return 对应枚举
     * @throws IllegalArgumentException 找不到时抛出
     */
    public static RoleEnum fromCode(int code) {
        for (RoleEnum role : values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("未知角色 code: " + code);
    }

    /**
     * 根据枚举名查枚举（接口传字符串用）
     *
     * @param name 枚举常量名
     * @return 对应枚举
     * @throws IllegalArgumentException 参数为 null 或找不到时抛出
     */
    public static RoleEnum fromName(String name) {
        for (RoleEnum role : values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("未知角色 name: " + name);
    }

    /**
     * 根据中文名查枚举（少用，中文名可能变）
     */
    public static RoleEnum fromRoleName(String roleName) {
        if (roleName == null) {
            throw new IllegalArgumentException("roleName 不能为 null（业务约束，疑似数据异常或被绕过）");
        }

        for (RoleEnum role : values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("未知角色 roleName: " + roleName);
    }

}