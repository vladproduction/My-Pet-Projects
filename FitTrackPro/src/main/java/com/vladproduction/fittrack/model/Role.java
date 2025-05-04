package com.vladproduction.fittrack.model;

import com.vladproduction.fittrack.model.enums.RoleType;

public class Role {

    private Long id;
    private RoleType roleType;

    public Role() {}

    public Role(Long id, RoleType roleType){
        this.id = id;
        this.roleType = roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return String.format("Role[id=%d, role='%s']",
                id, roleType);
    }
}
