package com.vladproduction;

import java.util.Map;

public class SocialShare implements ShareStrategy {
    @Override
    public void share(String content, Map<String, String> metadata) throws ShareException {
        if (content == null || content.isEmpty()) {
            throw new ShareException("Content cannot be empty");
        }
        String platform = metadata.getOrDefault("platform", "");
        if (platform.isEmpty()) {
            throw new ShareException("Social platform is required");
        }
        System.out.println("Sharing on " + platform + ": " + content);
    }
}
