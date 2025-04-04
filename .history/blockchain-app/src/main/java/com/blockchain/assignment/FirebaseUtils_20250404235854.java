package com.blockchain.assignment;

import utils.PasswordUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class FirebaseUtils {
    private FirebaseAuth auth;
    private DatabaseReference database;
    private Blockchain blockchain;  // 新增区块链对象

    public FirebaseUtils() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
        this.blockchain = new Blockchain();  // 初始化区块链（含创世区块）
    }

    // 注册用户并加密密码，同时将用户数据添加到区块链
    public void signUpUser(String email, String password) {
        // 使用 BCrypt 或 SHA-256 加密密码
        String hashedPassword = PasswordUtils.hashPassword(password);

        // 创建 Firebase 用户请求（Admin SDK方法）
        CreateRequest request = new CreateRequest()
            .setEmail(email)
            .setPassword(password);

        try {
            // 创建用户
            UserRecord userRecord = auth.createUser(request);
            String userId = userRecord.getUid();
            storeHashedPassword(userId, hashedPassword);

            // 构造一个数据块数据（例如：用户 ID 和邮箱）
            String blockData = "UserID:" + userId + ";Email:" + email + ";PasswordHash:" + hashedPassword;
            blockchain.addBlock(blockData);  // 添加到区块链中

            // 输出当前区块链的所有区块
            System.out.println("Blockchain:");
            for (Block block : blockchain.getChain()) {
                System.out.println(block);
            }

            // 可以进一步计算 Merkle 根
            // 假设你收集每个区块的 hash 值到一个 List<String> 中
            // List<String> blockHashes = blockchain.getChain().stream().map(Block::getHash).collect(Collectors.toList());
            // String merkleRoot = MerkleTree.buildMerkleRoot(blockHashes);
            // System.out.println("Merkle Root: " + merkleRoot);

            System.out.println("User registered successfully with UID: " + userId);
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // 存储加密后的密码到 Firebase 数据库
    private void storeHashedPassword(String userId, String hashedPassword) {
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("password", hashedPassword);
        database.child("users").child(userId).updateChildren(userUpdates, null);
    }
}
