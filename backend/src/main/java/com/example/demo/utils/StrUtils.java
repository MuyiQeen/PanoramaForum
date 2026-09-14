package com.example.demo.utils;

public final class StrUtils {

    private StrUtils() {}

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


}