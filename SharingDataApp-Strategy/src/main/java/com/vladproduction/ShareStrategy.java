package com.vladproduction;

import java.util.Map;

// First, let's improve the sharing strategy with proper parameters and error handling
public interface ShareStrategy {
    void share(String content, Map<String, String> metadata) throws ShareException;
}