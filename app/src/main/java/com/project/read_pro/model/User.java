package com.project.read_pro.model;


public class User {
    private int id;
    private String name;
    private String email;
    private String role;
    private String facebookId;
    private String googleId;
    private boolean isActive;

    public User(int id, String name, String email, String role, String facebookId, String googleId, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.facebookId = facebookId;
        this.googleId = googleId;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
