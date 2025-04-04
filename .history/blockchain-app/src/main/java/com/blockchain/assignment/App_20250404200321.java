package com.blockchain.assignment;

public class App {

    public static void main(String[] args) {
        // 初始化 Firebase
        FirebaseInitializer.initialize();
        
        // 创建 Firebase 身份验证服务实例
        FirebaseAuthService authService = new FirebaseAuthService();
        
        // 示例：注册用户
        authService.registerUser("testuser@example.com", "password123");
        
        // 示例：登录用户
        authService.loginUser("testuser@example.com", "password123");
    }
}
