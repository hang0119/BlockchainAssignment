package com.blockchain.assignment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    public static void initialize() {
        try {
            // Specify the path to your Firebase Admin SDK JSON file
            FileInputStream serviceAccount =
                    new FileInputStream("C:/Users/hang5/Blockchain/blockchain-app/firebase-adminsdk.json");

            @SuppressWarnings("deprecation")
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://blockchainassignment-default-rtdb.asia-southeast1.firebasedatabase.app/") // Set your Firebase Database URL here
                    .build();

            // Initialize Firebase with the provided options
            FirebaseApp.initializeApp(options);

            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing Firebase: " + e.getMessage());
        }
    }
}
