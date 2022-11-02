package com.gitlab.pawelkorniak.config;

import com.gitlab.pawelkorniak.facade.BookingFacadeImpl;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;
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
    public Map<Long, Event> eventStorage(){

        return isMemoryStorageSet ? new HashMap<>() : null;
    }

    @Bean
    public Map<Long, Ticket> ticketStorage(){
        return isMemoryStorageSet ? new HashMap<>() : null;
    }

    @Bean
    public Map<Long, User> userStorage(){
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
    public BookingFacadeImpl facade(){
        return new BookingFacadeImpl(eventService(),ticketService(),userService());
    }

}
