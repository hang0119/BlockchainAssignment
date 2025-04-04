package com.blockchain.assignment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String previousHash;
    private String data;
    private long timestamp;
    private String hash;

    public Block(String previousHash, String data, long timestamp) {
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = timestamp;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = previousHash + Long.toString(timestamp) + data;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }

    // Getter methods
    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "previousHash='" + previousHash + '\'' +
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                ", hash='" + hash + '\'' +
                '}';
    }
}
