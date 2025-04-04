package utils;=
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    // 使用SHA-256进行密码哈希
    public static String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainPassword.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();  // 返回哈希后的密码
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in SHA-256 hashing", e);
        }
    }

    // 验证密码与哈希密码是否匹配
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        String hashedInputPassword = hashPassword(plainPassword);
        return hashedInputPassword.equals(hashedPassword);  // 比对两个哈希值
    }
}
