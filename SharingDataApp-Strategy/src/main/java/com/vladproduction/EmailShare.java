package com.vladproduction;

import java.util.Map;

public class EmailShare implements ShareStrategy {
    @Override
    public void share(String content, Map<String, String> metadata) throws ShareException {
        if (content == null || content.isEmpty()) {
            throw new ShareException("Content cannot be empty");
        }
        String recipient = metadata.getOrDefault("recipient", "");
        if (recipient.isEmpty()) {
            throw new ShareException("Email recipient is required");
        }
        System.out.println("Sharing via Email to " + recipient + ": " + content);
    }
}

