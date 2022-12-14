package com.gitlab.pawelkorniak.module20.service;

import com.gitlab.pawelkorniak.module20.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Slf4j
public class Tools {

    Random random = new Random();
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createTable(String name, List<String> columns) {
        log.info("Creating table " + name + ", with columns: " + columns);
        jdbcTemplate.execute("DROP TABLE "+ name + " IF EXISTS");
        StringBuilder stringBuilder = new StringBuilder();
        columns.forEach(s -> stringBuilder.append(s + ","));
        String s1 = stringBuilder.toString();
        var s = s1.substring(0,s1.length()-2);
        jdbcTemplate.execute("CREATE TABLE "+ name + "(" + s + ")");
    }

    public void queryTableUsers(String name) {
        log.info("Querying for customer records where name = " + name);
        jdbcTemplate.query(
                "SELECT id, name, surname, birthdate FROM users WHERE name = ?", new Object[]{name},
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getDate("birthdate"))
        ).forEach(user -> log.info(user.toString()));
    }

    public Object getDate() {

        LocalDate date = LocalDate.of(2025, random.nextInt(12) + 1, random.nextInt(28) + 1);
        return date;

    }

    public void querry() {

        jdbcTemplate.query("" +
                        "SELECT users.id, users.name, users.surname, posts.text, COUNT(likes.id) " +
                        "FROM users " +
                        "JOIN posts " +
                        "ON users.id=posts.userid " +
                        "JOIN likes " +
                        "ON posts.id=likes.postid " +
                        "WHERE users.name = ? " +
                        "GROUP BY users.id " +
                                ""
                , new Object[]{UserServiceRunner.name},
                     (rs, rowNum) -> String.format("User: %s, posted: \"%s\" with %s likes and %s friends",rs.getString("name"), rs.getString("text"), rs.getInt(5), rs.getInt("id"))
                ).forEach(s -> log.info(s));
    }
}
