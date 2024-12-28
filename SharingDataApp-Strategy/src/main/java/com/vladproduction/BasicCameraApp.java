package com.vladproduction;

import java.util.HashMap;
import java.util.Map;

// Concrete implementations
class BasicCameraApp extends PhoneCameraApp {
//    @Override
//    protected void takePhoto() throws CameraException {
//        // Basic photo taking implementation
//        System.out.println("Taking basic photo");
//        // Simulate creating a photo
//        currentPhoto = new Photo(
//            new byte[100],
//            new HashMap<>(),
//            "JPG"
//        );
//    }
//
//    @Override
//    protected void savePhoto() throws CameraException {
//        System.out.println("Saving basic photo");
//    }

    //modification for noErrorScenario() in MainApp:

    @Override
    protected void takePhoto() throws CameraException {
        System.out.println("Taking basic photo");

        // Include required metadata for sharing
        Map<String, String> metadata = new HashMap<>();
        metadata.put("recipient", "user@example.com");  // Set default recipient

        currentPhoto = new Photo(
                new byte[100],
                metadata,
                "JPG"
        );
    }

    @Override
    protected void savePhoto() throws CameraException {
        System.out.println("Saving basic photo");
    }

}

