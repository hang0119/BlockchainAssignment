package com.blockchainapp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // 加密密码
    public static String hashPassword(String password) {
        // 使用 BCrypt 生成盐并进行密码哈希
        String salt = BCrypt.gensalt(12); // 12 是工作因子，越高越安全
        return BCrypt.hashpw(password, salt); // 返回哈希后的密码
    }

    // 验证密码
    public static boolean checkPassword(String password, String hashedPassword) {
        // 比较输入的密码和存储的哈希密码是否匹配
        return BCrypt.checkpw(password, hashedPassword);
    }
}
