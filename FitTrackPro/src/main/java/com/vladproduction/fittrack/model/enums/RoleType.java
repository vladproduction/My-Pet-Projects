package com.vladproduction.fittrack.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum RoleType {

    ADMIN,
    COACH,
    CLIENT;

    private static final Logger logger = LoggerFactory.getLogger(RoleType.class);

    public static RoleType fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return RoleType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            logger.error("Failed to convert string to RoleType: {}", ex.getMessage());
            return null;
        }
    }

}
