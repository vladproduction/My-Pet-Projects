package com.vladproduction.fittrack.model;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Long roleId;
    private boolean active;

    public User() {}

    public User(Long id, String username, String password, String email, Long roleId, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, username='%s', email='%s', roleId=%d, isActive=%s]",
                id, username, email, roleId, active);
    }
}
