package com.gitlab.pawelkorniak.clientapp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User  {
    private @Id
    @GeneratedValue Long id;
    private String name;

    private String password;

    public User(String name, String password) {

        this.name = name;
        this.password = password;
    }

}
