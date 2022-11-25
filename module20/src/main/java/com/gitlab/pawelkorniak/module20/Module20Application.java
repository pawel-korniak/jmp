package com.gitlab.pawelkorniak.module20;

import com.gitlab.pawelkorniak.module20.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class Module20Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Module20Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(" +
                "id SERIAL, name VARCHAR(255), surname VARCHAR(255), birthdate VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo 1.01.1990", "Jeff Dean 1.01.1990", "Josh Bloch 1.01.1990", "Josh Long 1.01.1990").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting user record for %s %s %s", name[0], name[1], name[2])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO users(name, surname, birthdate) VALUES (?,?,?)", splitUpNames);

        log.info("Querying for customer records where name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, name, surname, birthdate FROM users WHERE name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birthdate"))
        ).forEach(customer -> log.info(customer.toString()));
    }
}

