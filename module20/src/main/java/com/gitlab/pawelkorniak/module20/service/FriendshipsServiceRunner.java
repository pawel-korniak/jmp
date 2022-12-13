package com.gitlab.pawelkorniak.module20.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class FriendshipsServiceRunner implements Runnable{

    public static final int NUMBER_OF_FRIENDSHIPS = 70_000;
    public static final int NUMBER_OF_USERS = 1_000;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Tools tools;

    @Override
    public void run() {


        tools.createTable("friendships",List.of("id SERIAL","userid1 INT", "userid2 INT", "timestamp DATE)"));

        insertFriendships(getFakeFriendships());

    }



    private List<Object[]> getFakeFriendships() {
        List<Object[]> list = new ArrayList<>();
        var random = new Random();
        for (int i = 0; i < NUMBER_OF_FRIENDSHIPS; i++) {
            var object = new Object[3];
            object[0] = random.nextInt(NUMBER_OF_USERS);
            object[1] = random.nextInt(NUMBER_OF_USERS);
            object[2] = tools.getDate();
            list.add(object);
        }
        return list;
    }



    private void insertFriendships(List<Object[]> objects) {
        // Use a Java 8 stream to print out each tuple of the list
        log.info(String.format("Inserting %s friendships",objects.size()));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO friendships(userid1, userid2, timestamp) VALUES (?,?,?)", objects);
    }

}
