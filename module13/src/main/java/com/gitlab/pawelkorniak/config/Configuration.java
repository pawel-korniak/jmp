package com.gitlab.pawelkorniak.config;

import com.gitlab.pawelkorniak.facade.BookingFacade;
import com.gitlab.pawelkorniak.facade.BookingFacadeImpl;
import com.gitlab.pawelkorniak.service.EventService;
import com.gitlab.pawelkorniak.service.TicketService;
import com.gitlab.pawelkorniak.service.UserService;

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
    public Storage storage() {
        if (memoryStorage){
            logger.info("Memory storage will be used");
        }
        return memoryStorage ? new Storage() : null;
    }

    @Bean
    public EventService eventService() {
        return new EventService();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public BookingFacade facade() {
        return new BookingFacadeImpl(eventService(), ticketService(), userService());
    }

}
