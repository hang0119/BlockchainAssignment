package com.blockchain.assignment;

public class App {

    public static void main(String[] args) {
        // 初始化 Firebase
        FirebaseConfig.initialize();
        
        // 创建 Firebase 身份验证服务实例
        AuthService authService = new AuthService();
        
        // 示例：注册用户
        String registerMessage = authService.registerUser("testuser@example.com", "password123");
        System.out.println(registerMessage); // 输出注册结果
        
        // 示例：登录用户
        String loginMessage = authService.loginUser("testuser@example.com", "password123");
        System.out.println(loginMessage); // 输出登录结果
    }
}
