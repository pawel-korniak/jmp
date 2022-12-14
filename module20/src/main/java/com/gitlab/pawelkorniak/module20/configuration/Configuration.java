package com.gitlab.pawelkorniak.module20.configuration;

import com.gitlab.pawelkorniak.module20.service.*;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public LikeServiceRunner likeServiceRunner(){
        return new LikeServiceRunner();
    }

    @Bean
    public PostServiceRunner postServiceRunner(){
        return new PostServiceRunner();
    }
    @Bean
    public UserServiceRunner userServiceRunner(){
        return new UserServiceRunner();
    }

    @Bean
    public FriendshipsServiceRunner friendshipsServiceRunner(){
        return new FriendshipsServiceRunner();
    }

    @Bean
    public Tools tools(){
        return new Tools();
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }
}
