package com.gitlab.pawelkorniak.module20.service;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.*;

@Slf4j
public class PostServiceRunner implements Runnable{

    public static final int NUMBER_OF_USERS = 1_000;
    public static final String TABLE_NAME = "posts";

    @Autowired
    Faker faker;
    @Autowired
    Tools tools;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run() {
        tools.createTable(TABLE_NAME,List.of("id SERIAL","userid INT", "text VARCHAR(255)", "timestamp DATE)"));
        insertUsers(getFakeUsers());
    }


    private List<Object[]> getFakeUsers() {
        List<Object[]> list = new ArrayList<>();
        var random = new Random();
        for (int i = 0; i < NUMBER_OF_USERS; i++){
            var object = new Object[3];
            object[0]= i;
            object[1]= faker.famousLastWords().lastWords();
            object[2]= tools.getDate();
            list.add(object);

        }
        return list;
    }

    private void insertUsers(List<Object[]> objects) {
        // Use a Java 8 stream to print out each tuple of the list
        log.info(String.format("Inserting %s posts",objects.size()));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO posts(userid, text, timestamp) VALUES (?,?,?)", objects);
    }
}
