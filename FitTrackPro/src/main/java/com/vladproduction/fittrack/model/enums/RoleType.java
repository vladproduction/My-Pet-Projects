package com.vladproduction.fittrack.model.enums;

public enum RoleType {

    ADMIN,
    COACH,
    CLIENT;

    public static RoleType fromString(String value){
        if(value == null){
            return null;
        }
        return RoleType.valueOf(value.toUpperCase());
    }

}
