package com.blockchain.assignment;

import utils.PasswordUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class FirebaseUtils {

    private FirebaseAuth auth;
    private DatabaseReference database;

    public FirebaseUtils() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    // 注册用户并加密密码
    public void signUpUser(String email, String password) {
        // 使用 BCrypt 加密密码
        String hashedPassword = PasswordUtils.hashPassword(password);

        // 创建 Firebase 用户创建请求
        CreateRequest request = new CreateRequest()
            .setEmail(email)
            .setPassword(password);

        try {
            // 创建用户
            UserRecord userRecord = auth.createUser(request);

            // 用户创建成功，存储加密后的密码
            String userId = userRecord.getUid();
            storeHashedPassword(userId, hashedPassword);

            System.out.println("User registered successfully with UID: " + userId);
        } catch (Exception e) {
            // 错误处理
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // 存储加密后的密码
    private void storeHashedPassword(String userId, String hashedPassword) {
        // 使用 Map 来更新数据
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("password", hashedPassword);

        database.child("users").child(userId).updateChildren(userUpdates)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    System.out.println("Password stored successfully.");
                } else {
                    System.out.println("Error storing password: " + task.getException().getMessage());
                }
            });
    }
}
