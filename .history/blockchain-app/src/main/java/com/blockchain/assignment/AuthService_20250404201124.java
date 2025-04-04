package com.blockchain.assignment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
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

    // 登录用户（验证邮箱和密码）
    public static String loginUser(String email, String password) {
        try {
            // 使用 Firebase 的 signInWithEmailAndPassword 方法进行登录验证
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        System.out.println("Login successful: Welcome " + user.getEmail());
                    } else {
                        System.out.println("Login failed: " + task.getException().getMessage());
                    }
                });

            return "Login attempt initiated.";  // 登录尝试已开始
        } catch (FirebaseAuthException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
