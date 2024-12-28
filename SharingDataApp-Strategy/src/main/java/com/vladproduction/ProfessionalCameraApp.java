package com.vladproduction;

import java.util.HashMap;
import java.util.Map;

class ProfessionalCameraApp extends PhoneCameraApp {
    @Override
    protected void takePhoto() throws CameraException {
        System.out.println("Taking professional RAW photo");
        // Simulate creating a high-quality photo
        Map<String, String> metadata = new HashMap<>();
        metadata.put("iso", "100");
        metadata.put("shutterSpeed", "1/1000");
        metadata.put("aperture", "f/2.8");

        currentPhoto = new Photo(
                new byte[1000],
                metadata,
                "RAW"
        );
    }

    @Override
    protected void savePhoto() throws CameraException {
        System.out.println("Saving professional photo with metadata");
    }
}
