package com.github.pawelkorniak.module33.rest.config;

import com.github.pawelkorniak.module33.api.EventService;
import com.github.pawelkorniak.module33.impl.EventServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public EventService service(){
        return new EventServiceImpl();
    }
}

