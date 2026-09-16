package com.example.demo.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomGenerateUsernameUtils {

    /**
     * 随机生成用户名
     * @return 用户名
     */
    public String generateUsername(){
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        String prefix = "user_";
        StringBuilder username = new StringBuilder(prefix);
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 8; i++) username.append(chars.charAt(r.nextInt(chars.length())));

        return username.toString();
    }

}
