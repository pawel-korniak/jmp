package com.gitlab.pawelkorniak.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitlab.pawelkorniak.config.Storage;
import com.gitlab.pawelkorniak.dao.EventDAO;
import com.gitlab.pawelkorniak.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EventService {
    private static long nextId;
    private static final String prefix = "event:";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Storage events;

    Logger logger = Logger.getLogger(EventService.class.getName());

    public Event getEventById(long eventId){
        try {
            return objectMapper.readValue(events.get(prefix + eventId),Event.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum){
        return events.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value,Event.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());
    }


    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum){
        return events.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value,Event.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event){
        Event newEvent = new EventDAO(nextId,event);
        try {
            events.put(prefix + newEvent.getId(), objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        nextId++;
        logger.info("Event created: " + event);
        return newEvent;
    }

    //TODO empty id proof
    public Event updateEvent(Event event){
        return createEvent(event);
    }


   public boolean deleteEvent(long eventId){
        logger.info("Event deleted: " + eventId);
        return events.remove(prefix + eventId) ;
    }
}
