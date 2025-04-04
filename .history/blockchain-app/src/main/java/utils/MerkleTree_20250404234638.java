package utils;  // 如果你想放在 utils 文件夹下，可以改成这个包名

import java.util.List;
import java.util.ArrayList;

public class MerkleTree {

    // 构建 Merkle 根
    public static String buildMerkleRoot(List<String> data) {
        if (data.size() == 1) {
            return data.get(0);  // 如果只有一个元素，返回该元素
        }

        List<String> newLevel = new ArrayList<>();
        for (int i = 0; i < data.size(); i += 2) {
            String left = data.get(i);
            String right = (i + 1 < data.size()) ? data.get(i + 1) : left;  // 如果数据为奇数个，重复最后一个
            newLevel.add(PasswordUtils.hashPassword(left + right));  // 将左右合并，进行哈希处理
        }

        return buildMerkleRoot(newLevel);  // 递归构建 Merkle 根
    }
}
