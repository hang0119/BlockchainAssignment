package com.blockchain.assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MerkleTreeTest {
    public static void main(String[] args) {
        // 构造一些示例数据，比如区块数据
        List<String> sampleData = Arrays.asList("Transaction1", "Transaction2", "Transaction3", "Transaction4");
        
        // 对每条数据先计算哈希
        List<String> hashedData = sampleData.stream()
                .map(data -> HashUtil.applySHA256(data))
                .collect(Collectors.toList());
        
        // 计算 Merkle 根
        String merkleRoot = MerkleTree.buildMerkleRoot(hashedData);
        
        System.out.println("Merkle Root: " + merkleRoot);
    }
}

