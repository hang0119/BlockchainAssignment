package com.blockchain.assignment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class AuthService {

    public static String registerUser(String email, String password) {
        try {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(hashedPassword);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "User registered successfully: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    public static String loginUser(String email, String password) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);

            if (BCrypt.verifyer().verify(password.toCharArray(), userRecord.getPasswordHash().toCharArray()).verified) {
                return "Login successful: Welcome " + userRecord.getEmail();
            } else {
                return "Login failed: Incorrect password";
            }
        } catch (FirebaseAuthException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
