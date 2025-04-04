package com.blockchain.assignment;

public class App {
    public static void main(String[] args) {
        FirebaseConfig.initialize();

        String registerResult = AuthService.registerUser("testuser@example.com", "password123");
        System.out.println(registerResult);

        String loginResult = AuthService.loginUser("testuser@example.com", "password123");
        System.out.println(loginResult);
    }
}
