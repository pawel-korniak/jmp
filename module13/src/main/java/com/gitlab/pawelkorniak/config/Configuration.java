package com.gitlab.pawelkorniak.config;

import com.gitlab.pawelkorniak.facade.BookingFacadeImpl;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.service.EventService;
import com.gitlab.pawelkorniak.service.TicketService;
import com.gitlab.pawelkorniak.service.UserService;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Map<Long, Event> storage(){
        return new HashMap<>();
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
    public BookingFacadeImpl facade(){
        return new BookingFacadeImpl(eventService(),ticketService(),userService());
    }

}
