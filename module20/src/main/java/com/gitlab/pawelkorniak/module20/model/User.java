package com.gitlab.pawelkorniak.module20.model;

import lombok.ToString;

import java.util.Date;
@ToString
public class User {
    long id;
    String name;
    String surname;
    Date birthdate;

    public User(long id, String name, String surname, Date birthdate) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }
}
