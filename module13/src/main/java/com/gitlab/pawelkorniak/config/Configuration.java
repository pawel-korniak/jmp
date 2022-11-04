package com.gitlab.pawelkorniak.config;

import com.gitlab.pawelkorniak.facade.BookingFacade;
import com.gitlab.pawelkorniak.facade.BookingFacadeImpl;
import com.gitlab.pawelkorniak.service.EventService;
import com.gitlab.pawelkorniak.service.TicketService;
import com.gitlab.pawelkorniak.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("memoryStorage")
    boolean isMemoryStorageSet;

    @Bean
    public Map<String, String> storage(){
        return isMemoryStorageSet ? new HashMap<>() : null;
    }

    @Bean
    public EventService eventService(){
        return new EventService();
    }

    @Bean
    public TicketService ticketService(){
        return new TicketService();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public BookingFacade facade(){
        return new BookingFacadeImpl(eventService(),ticketService(),userService());
    }

}
