package com.CapstoneProject.MindMosaic.utils;

import java.util.UUID;

public class UniqueCodeGenerator {

    // Generates a short, unique caregiver code
    public static String generateCaregiverCode() {
        return "MM-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}
