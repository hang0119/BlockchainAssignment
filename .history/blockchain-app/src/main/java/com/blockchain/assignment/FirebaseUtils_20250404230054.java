package com.blockchain.assignment;

import utils.PasswordUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    private FirebaseAuth auth;
    private DatabaseReference database;

    public FirebaseUtils() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
    }

  public void signUpUser(String email, String password) {
    // 加密密码（这里使用了 BCrypt，你可以选择是否存储它，Firebase 自己会 hash 密码）
    String hashedPassword = PasswordUtils.hashPassword(password);

    // 创建用户的请求
    CreateRequest request = new CreateRequest()
            .setEmail(email)
            .setPassword(password);

    try {
        // 调用 Firebase Admin SDK 来创建用户
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // 如果成功，打印用户 ID
        System.out.println("User registered successfully: " + userRecord.getUid());

        // 可选：你也可以选择存储加密的密码（虽然 Firebase 自己会处理）
        storeHashedPassword(userRecord.getUid(), hashedPassword);
    } catch (Exception e) {
        // 捕获创建用户时的错误并打印
        System.out.println("Error creating user: " + e.getMessage());
    }
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
