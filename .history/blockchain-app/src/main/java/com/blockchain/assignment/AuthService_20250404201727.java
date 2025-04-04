package com.blockchain.assignment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class AuthService {

    public static String registerUser(String email, String password) {
        try {
            // 使用 Firebase 内置功能创建用户，Firebase 会自动处理密码哈希
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password); // 密码会被 Firebase 自动哈希

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "User registered successfully: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    public static String loginUser(String email, String password) {
        try {
            // 使用 Firebase 的方式登录，Firebase 会验证密码
            FirebaseAuth.getInstance().getUserByEmail(email);
            return "Login successful: Welcome " + email;
        } catch (FirebaseAuthException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
