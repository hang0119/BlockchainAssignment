package com.blockchain.assignment;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {

    public static void initialize() {
        try {
            FileInputStream serviceAccount =
                new FileInputStream("C:/path/to/your/firebase-adminsdk.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://your-database-url.firebaseio.com/")
                .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase initialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
