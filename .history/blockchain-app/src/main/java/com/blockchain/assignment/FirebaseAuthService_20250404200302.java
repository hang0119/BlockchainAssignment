import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

public class FirebaseAuthService {
    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // 注册新用户
    public void registerUser(String email, String password) {
        try {
            CreateRequest request = new CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            UserRecord userRecord = firebaseAuth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    // 登录用户
    public void loginUser(String email, String password) {
        try {
            firebaseAuth.getUserByEmail(email);
            // 在这里可以使用 Firebase 提供的其他方式来验证密码（如果有必要）
            System.out.println("Login successful: Welcome " + email);
        } catch (FirebaseAuthException e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }
}
