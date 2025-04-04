import utils.MerkleTree;
import java.util.List;
import java.util.ArrayList;

public class FirebaseUtils {
    private FirebaseAuth auth;
    private DatabaseReference database;

    public FirebaseUtils() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    // 注册用户并加密密码
    public void signUpUser(String email, String password) {
        // 使用 BCrypt 加密密码
        String hashedPassword = PasswordUtils.hashPassword(password);

        // 创建 Firebase 用户创建请求
        CreateRequest request = new CreateRequest()
            .setEmail(email)
            .setPassword(password);

        try {
            // 创建用户
            UserRecord userRecord = auth.createUser(request);

            // 获取用户ID
            String userId = userRecord.getUid();

            // 存储加密后的密码
            storeHashedPassword(userId, hashedPassword);

            // 将所有用户的哈希密码放到列表中
            List<String> allHashedPasswords = getAllUserHashedPasswords();
            allHashedPasswords.add(hashedPassword);  // 添加当前用户的密码哈希

            // 生成 Merkle 根
            String merkleRoot = MerkleTree.buildMerkleRoot(allHashedPasswords);

            // 存储 Merkle 根
            storeMerkleRoot(merkleRoot);

            System.out.println("User registered successfully with UID: " + userId);
        } catch (Exception e) {
            // 错误处理
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // 存储加密后的密码
    private void storeHashedPassword(String userId, String hashedPassword) {
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("password", hashedPassword);
        database.child("users").child(userId).updateChildren(userUpdates, null);
    }

    // 获取所有用户的哈希密码（可以通过查询数据库获得）
    private List<String> getAllUserHashedPasswords() {
        // 这里可以从数据库中查询所有用户的哈希密码
        return new ArrayList<>();
    }

    // 存储 Merkle 根
    private void storeMerkleRoot(String merkleRoot) {
        // 存储 Merkle 根到数据库（你可以选择合适的位置）
        database.child("merkleRoots").push().setValue(merkleRoot);
    }
}
