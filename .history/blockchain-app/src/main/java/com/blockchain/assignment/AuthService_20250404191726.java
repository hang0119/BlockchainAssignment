package com.blockchain.assignment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class AuthService {

    // 注册功能
    public static String registerUser(String email, String password) {
        try {
            // 密码加密
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

            // 创建用户
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(hashedPassword);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "注册成功，用户ID: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "注册失败: " + e.getMessage();
        }
    }

    // 登录功能
    public static String loginUser(String email, String password) {
        try {
            // 查找用户
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            
            // 校验密码（这里只是模拟了密码验证，真实场景中可以通过自己的方法）
            if (BCrypt.verifyer().verify(password.toCharArray(), userRecord.getPasswordHash().toCharArray()).verified) {
                return "登录成功，欢迎 " + userRecord.getEmail();
            } else {
                return "登录失败: 密码不正确";
            }
        } catch (FirebaseAuthException e) {
            return "登录失败: " + e.getMessage();
        }
    }
}
