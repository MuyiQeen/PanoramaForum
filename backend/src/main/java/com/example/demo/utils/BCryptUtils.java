package com.example.demo.utils;

import com.example.demo.constant.PasswordConstants;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorEnum;
import org.mindrot.jbcrypt.BCrypt;

public final class BCryptUtils {

    private BCryptUtils() {}

    /**
     * 加密明文密码
     * 输入明文密码进行哈希加密，如果明文密码为空或加密失败都会抛出异常
     *
     * @param password 明文密码
     * @return 加密后的密码 或 抛出异常
     */
    public static String hashPassword(String password) {

        if (StrUtils.isEmpty(password)) {
            throw new AppException(ErrorEnum.PARAM_INVALID,"用户密码为空");
        }
        try {
            return BCrypt.hashpw(password, BCrypt.gensalt(PasswordConstants.BCRYPT_WORK_FACTOR));
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorEnum.SYSTEM_ERROR, "密码加密失败", e);
        }
    }

    /**
     * 验证明文密码和加密后的密码是否一致
     * 一致则返回true
     * 不一致返回false
     *
     * @param password 明文密码
     * @param hashedPassword 加密密码
     * @return 布尔值
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        if (StrUtils.isEmpty(password) || StrUtils.isEmpty(hashedPassword)) {
            return false;
        }
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }

    }


}
