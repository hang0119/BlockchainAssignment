package com.blockchain.assignment;

import utils.PasswordUtils; // ✅ 修正 import 路径
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        // 使用 Firebase 进行注册
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // 注册成功，存储加密后的密码
                    String userId = task.getResult().getUser().getUid();
                    storeHashedPassword(userId, hashedPassword);
                    System.out.println("User registered successfully.");
                } else {
                    System.out.println("Error: " + task.getException().getMessage());
                }
            });
    }

    // 存储加密后的密码
    private void storeHashedPassword(String userId, String hashedPassword) {
        database.child("users").child(userId).child("password").setValue(hashedPassword)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    System.out.println("Password stored successfully.");
                } else {
                    System.out.println("Error storing password: " + task.getException().getMessage());
                }
            });
    }
}
