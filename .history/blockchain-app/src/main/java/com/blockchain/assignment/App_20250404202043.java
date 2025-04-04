public class App {
    public static void main(String[] args) {
        // 示例：注册新用户
        String signupResult = FirebaseAuthService.signup("testuser@example.com", "password123");
        if (signupResult != null) {
            System.out.println("User registered successfully with ID: " + signupResult);
        } else {
            System.out.println("Registration failed.");
        }

        // 示例：登录用户
        String signinResult = FirebaseAuthService.signin("testuser@example.com", "password123");
        System.out.println(signinResult);
    }
}
