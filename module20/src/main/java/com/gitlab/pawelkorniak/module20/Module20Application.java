package com.gitlab.pawelkorniak.module20;

import com.gitlab.pawelkorniak.module20.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@Slf4j
@SpringBootApplication
public class Module20Application implements CommandLineRunner {

    @Autowired
    UserServiceRunner userServiceRunner;

    @Autowired
    PostServiceRunner postServiceRunner;

    @Autowired
    LikeServiceRunner likeServiceRunner;

    @Autowired
    FriendshipsServiceRunner friendshipsServiceRunner;

    @Autowired
    Tools tools;

    public static void main(String[] args) {
        SpringApplication.run(Module20Application.class, args);
    }

    @Override
    public void run(String... args) {

        List<Runnable> list = List.of(userServiceRunner,friendshipsServiceRunner,postServiceRunner,likeServiceRunner);
        list.forEach(Runnable::run);
        tools.queryTableUsers(UserServiceRunner.name);
        tools.querry();
    }





}

