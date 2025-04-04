package com.blockchain.assignment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MerkleTreeTest {
    public static void main(String[] args) {
        MerkleTree tree = new MerkleTree();
        tree.addData("Test Data 1");
        tree.addData("Test Data 2");
        tree.buildTree();

        System.out.println("Merkle Root: " + tree.getRootHash());
        // 或者输出树的其他内容来验证
    }
}


