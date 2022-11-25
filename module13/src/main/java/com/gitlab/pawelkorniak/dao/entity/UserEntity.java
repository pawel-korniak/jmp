package com.gitlab.pawelkorniak.dao.entity;

import com.gitlab.pawelkorniak.model.User;

public class UserEntity implements User {

    private long id;
    private String name;
    private String email;

    public UserEntity(User user) {
        this.id = id;
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UserEntity(long userId) {
        this.id = userId;
    }

    public UserEntity() {

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
