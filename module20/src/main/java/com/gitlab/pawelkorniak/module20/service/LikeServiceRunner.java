package com.gitlab.pawelkorniak.module20.service;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.*;

@Slf4j
public class LikeServiceRunner implements Runnable{

    public static final int NUMBER_OF_LIKES = 300_000;
    public static final int NUMBER_OF_POSTS = 1_000;
    public static final int NUMBER_OF_USERS = 1_000;
    public static final String TABLE_NAME = "likes";

    @Autowired
    Faker faker;
    @Autowired
    Tools tools;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run() {
        tools.createTable(TABLE_NAME,List.of("id SERIAL","postid INT", "userid INT", "timestamp DATE)"));
        insertUsers(getFakeUsers());
    }


    private List<Object[]> getFakeUsers() {
        List<Object[]> list = new ArrayList<>();
        var random = new Random();
        for (int i = 0; i < NUMBER_OF_LIKES; i++){
            var object = new Object[3];
            object[0]= random.nextInt(NUMBER_OF_USERS);
            object[1]= random.nextInt(NUMBER_OF_POSTS);
            object[2]= tools.getDate();
            list.add(object);

        }
        return list;
    }

    private void insertUsers(List<Object[]> objects) {
        // Use a Java 8 stream to print out each tuple of the list
        log.info(String.format("Inserting %s likes",objects.size()));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO likes(userid, postid, timestamp) VALUES (?,?,?)", objects);
    }
}
