package com.gitlab.pawelkorniak.config;

import com.gitlab.pawelkorniak.facade.BookingFacade;
import com.gitlab.pawelkorniak.facade.BookingFacadeImpl;
import com.gitlab.pawelkorniak.service.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import java.util.logging.Logger;

@PropertySource("properties.properties")
@org.springframework.context.annotation.Configuration
public class Configuration {

    Logger logger = java.util.logging.Logger.getLogger(Configuration.class.getName());

    @Value("${memoryStorage}")
    boolean memoryStorage;

    @Bean
    public EventService eventService() {
        return new EventServiceImpl();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public BookingFacade facade() {
        return new BookingFacadeImpl(eventService(), ticketService(), userService());
    }

}
