package com.github.pawelkorniak.module33.impl;

import com.github.pawelkorniak.module33.dto.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {


    EventServiceImpl eventService = new EventServiceImpl();

    @Test
    void createEvent() {
        eventService.createEvent(new Event());

        assertTrue(!eventService.getAllEvents().isEmpty());
    }

    @Test
    void deleteEvent() {
        eventService.createEvent(new Event());
        eventService.deleteEvent(0);
        assertTrue(eventService.getAllEvents().isEmpty());
    }

    @Test
    void getAllEvents() {
        eventService.createEvent(new Event());
        eventService.createEvent(new Event());
        eventService.createEvent(new Event());
        assertTrue(eventService.getAllEvents().size()==3);
    }

    @Test
    void getAllEventsByTitle() {
        Event event = new Event();
        event.setTitle("test");
        eventService.createEvent(event);
        assertTrue(!eventService.getAllEventsByTitle("test").isEmpty());
    }
}