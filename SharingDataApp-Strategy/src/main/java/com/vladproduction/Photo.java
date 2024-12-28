package com.vladproduction;

import java.util.Map;

// Introduce Photo class to encapsulate image data and metadata
public class Photo {
    private final byte[] imageData;
    private final Map<String, String> metadata;
    private final String format;

    public Photo(byte[] imageData, Map<String, String> metadata, String format) {
        this.imageData = imageData;
        this.metadata = metadata;
        this.format = format;
    }

    public byte[] getImageData() { return imageData; }
    public Map<String, String> getMetadata() { return metadata; }
    public String getFormat() { return format; }
}