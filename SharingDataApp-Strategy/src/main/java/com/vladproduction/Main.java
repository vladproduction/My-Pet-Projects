package com.vladproduction;

import java.util.HashMap;
import java.util.Map;

// Example usage
class Main {
    public static void main(String[] args) {
//        errorScenario();
        noErrorScenario();
    }

    private static void errorScenario() {
        try {
            // Basic camera example
            PhoneCameraApp basicApp = new BasicCameraApp();
            basicApp.setShareStrategy(new EmailShare());

            Map<String, String> emailMetadata = new HashMap<>();
            emailMetadata.put("recipient", "user@example.com");
            basicApp.processPhoto();

            // Professional camera example
            PhoneCameraApp proApp = new ProfessionalCameraApp();
            proApp.setShareStrategy(new SocialShare());

            Map<String, String> socialMetadata = new HashMap<>();
            socialMetadata.put("platform", "Instagram");
            proApp.processPhoto();

        } catch (CameraException e) {
            System.err.println("Camera error: " + e.getMessage());
        }
    }

    private static void noErrorScenario() {
        try {
            // Basic camera example
            BasicCameraApp basicApp = new BasicCameraApp();

            // Create email share strategy
            EmailShare emailShare = new EmailShare();
            basicApp.setShareStrategy(emailShare);

            // Set up the photo metadata with required email recipient
            Map<String, String> metadata = new HashMap<>();
            metadata.put("recipient", "user@example.com");

            // Create photo with metadata
            Photo photo = new Photo(
                    new byte[100],
                    metadata,
                    "JPG"
            );

            basicApp.processPhoto();

        } catch (CameraException e) {
            System.err.println("Camera error: " + e.getMessage());
        }
    }

}