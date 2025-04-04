package com.blockchain.assignment;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // 创建创世区块
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("0", "Genesis Block", System.currentTimeMillis());
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(String data) {
        Block newBlock = new Block(getLatestBlock().getHash(), data, System.currentTimeMillis());
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }
}
