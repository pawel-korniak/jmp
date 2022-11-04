package com.gitlab.pawelkorniak.config;


import com.gitlab.pawelkorniak.facade.BookingFacade;
import com.gitlab.pawelkorniak.service.EventService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Deployer {

    private final BookingFacade bookingFacade;

    Logger logger = Logger.getLogger(Deployer.class.getName());

    public Deployer(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @EventListener(ApplicationReadyEvent.class)
    void save() {

        logger.info("Deployer fired");


    }
}



