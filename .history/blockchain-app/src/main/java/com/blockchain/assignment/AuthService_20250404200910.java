package com.blockchain.assignment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class AuthService {

    // 注册用户
    public static String registerUser(String email, String password) {
        try {
            // 使用 Firebase 内置功能创建用户，Firebase 会自动处理密码哈希
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password); // 密码会被 Firebase 自动哈希

            // 创建用户并返回用户 UID
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "User registered successfully: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    // 登录用户（当前只是检查用户是否存在）
    public static String loginUser(String email, String password) {
        try {
            // 获取用户信息来验证用户是否存在
            FirebaseAuth.getInstance().getUserByEmail(email);
            // 如果用户存在，这里可以进一步处理密码验证
            return "Login successful: Welcome " + email;
        } catch (FirebaseAuthException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
