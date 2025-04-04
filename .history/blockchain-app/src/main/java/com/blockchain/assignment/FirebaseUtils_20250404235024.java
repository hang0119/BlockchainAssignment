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
        // 使用自定义的 PasswordUtils 加密密码
        String hashedPassword = PasswordUtils.hashPassword(password);

        // 创建用户请求（使用 Admin SDK）
        CreateRequest request = new CreateRequest()
            .setEmail(email)
            .setPassword(password);

        try {
            // 创建用户
            UserRecord userRecord = auth.createUser(request);
            String userId = userRecord.getUid();
            storeHashedPassword(userId, hashedPassword);
            System.out.println("User registered successfully with UID: " + userId);
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // 存储加密后的密码到 Firebase 数据库
    private void storeHashedPassword(String userId, String hashedPassword) {
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("password", hashedPassword);

        database.child("users").child(userId).updateChildren(userUpdates, null);
    }
}
