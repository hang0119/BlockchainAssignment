package com.blockchain.assignment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {

    @SuppressWarnings("deprecation")
    public static void initialize() {
        try {
            // 指定 Firebase Admin SDK JSON 文件的路径
            FileInputStream serviceAccount =
                    new FileInputStream("C:/Users/hang5/Blockchain/blockchain-app/firebase-adminsdk.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://blockchainassignment-default-rtdb.asia-southeast1.firebasedatabase.app/") // 配置你的 Firebase 数据库 URL
                    .build();

            // 初始化 Firebase
            FirebaseApp.initializeApp(options);

            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing Firebase: " + e.getMessage());
        }
    }
}
