package com.project.read_pro.response;

import com.project.read_pro.model.User;

public class LoginSignupResponse {
    private User user;
    private String token;
    private boolean error;
    private String message;

    public LoginSignupResponse(String message){
        this.message = message;
        this.error = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
