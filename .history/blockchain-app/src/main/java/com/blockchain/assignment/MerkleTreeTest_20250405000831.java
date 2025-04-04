package com.blockchain.assignment;

import java.util.Arrays;
import java.util.List;

public class MerkleTreeTest {
    public static void main(String[] args) {
        // 构造一些模拟的哈希值，假设这些是数据块的哈希
        List<String> sampleHashes = Arrays.asList(
            HashUtil.applySHA256("data1"),
            HashUtil.applySHA256("data2"),
            HashUtil.applySHA256("data3"),
            HashUtil.applySHA256("data4")
        );
        
        // 调用 MerkleTree 来生成 Merkle 根
        String merkleRoot = MerkleTree.buildMerkleRoot(sampleHashes);
        
        System.out.println("Merkle Root: " + merkleRoot);
    }
}
