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

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("package com.blockchain.assignment;\r\n" + //
                                                "\r\n" + //
                                                "import com.google.firebase.FirebaseApp;\r\n" + //
                                                "import com.google.firebase.FirebaseOptions;\r\n" + //
                                                "import java.io.FileInputStream;\r\n" + //
                                                "import java.io.IOException;\r\n" + //
                                                "\r\n" + //
                                                "public class FirebaseConfig {\r\n" + //
                                                "    public static void initialize() {\r\n" + //
                                                "        try {\r\n" + //
                                                "            // Specify the path to your Firebase Admin SDK JSON file\r\n" + //
                                                "            FileInputStream serviceAccount =\r\n" + //
                                                "                    new FileInputStream(\"C:/Users/hang5/Blockchain/blockchain-app/firebase-adminsdk.json\");\r\n" + //
                                                "\r\n" + //
                                                "            FirebaseOptions options = new FirebaseOptions.Builder()\r\n" + //
                                                "                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(serviceAccount))\r\n" + //
                                                "                    .setDatabaseUrl(\"https://your-database-name.firebaseio.com\") // Set your Firebase Database URL here\r\n" + //
                                                "                    .build();\r\n" + //
                                                "\r\n" + //
                                                "            // Initialize Firebase with the provided options\r\n" + //
                                                "            FirebaseApp.initializeApp(options);\r\n" + //
                                                "\r\n" + //
                                                "            System.out.println(\"Firebase initialized successfully.\");\r\n" + //
                                                "        } catch (IOException e) {\r\n" + //
                                                "            e.printStackTrace();\r\n" + //
                                                "            System.out.println(\"Error initializing Firebase: \" + e.getMessage());\r\n" + //
                                                "        }\r\n" + //
                                                "    }\r\n" + //
                                                "}\r\n" + //
                                                "") // Set your Firebase Database URL here
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
