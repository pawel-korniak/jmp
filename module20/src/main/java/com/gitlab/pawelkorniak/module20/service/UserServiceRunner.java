package com.gitlab.pawelkorniak.module20.service;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Slf4j
public class UserServiceRunner implements Runnable{

    public static final int NUMBER_OF_USERS = 10_000;

    public static String name;

    @Autowired
    Faker faker;
    @Autowired
    Tools tools;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run() {
        tools.createTable("users",List.of("id SERIAL","name VARCHAR(255)", "surname VARCHAR(255)", "birthdate DATE)"));
        insertUsers(getFakeUsers());
    }


    private List<Object[]> getFakeUsers() {
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_USERS; i++){
            var object = new Object[3];
            object[0]= faker.name().firstName();
            object[1]= faker.name().lastName();
            object[2]= tools.getDate();
            list.add(object);
            if(i==1){
                name = object[0].toString();
            }
        }
        return list;
    }

    private void insertUsers(List<Object[]> objects) {
        log.info(String.format("Inserting %s users",objects.size()));

        jdbcTemplate.batchUpdate("INSERT INTO users(name, surname, birthdate) VALUES (?,?,?)", objects);
    }
}
