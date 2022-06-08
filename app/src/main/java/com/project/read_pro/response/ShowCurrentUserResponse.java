package com.project.read_pro.response;

import com.project.read_pro.model.User;

public class ShowCurrentUserResponse {
    private User user;
    private int code;

    public ShowCurrentUserResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
