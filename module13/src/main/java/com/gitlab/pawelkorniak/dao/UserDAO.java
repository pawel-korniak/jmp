package com.gitlab.pawelkorniak.dao;

import com.gitlab.pawelkorniak.model.User;

public class UserDAO implements User {

    private long id;
    private String name;
    private String email;

    public UserDAO(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.email = user.getEmail();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
