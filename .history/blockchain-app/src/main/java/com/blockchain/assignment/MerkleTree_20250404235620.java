package com.blockchain.assignment;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    public static String buildMerkleRoot(List<String> hashes) {
        if (hashes.size() == 0) {
            return "";
        }
        if (hashes.size() == 1) {
            return hashes.get(0);
        }

        List<String> newLevel = new ArrayList<>();
        for (int i = 0; i < hashes.size(); i += 2) {
            String left = hashes.get(i);
            String right = (i + 1 < hashes.size()) ? hashes.get(i + 1) : left;
            // 这里使用 Block 中的 SHA-256 方法计算哈希，你也可以调用 HashUtil.applySHA256()
            String combinedHash = HashUtil.applySHA256(left + right);
            newLevel.add(combinedHash);
        }

        return buildMerkleRoot(newLevel);
    }
}
