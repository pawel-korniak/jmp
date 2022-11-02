package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.EventDAO;
import com.gitlab.pawelkorniak.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventService {
    private static long nextId;

    @Autowired
    Map<Long, Event> events;

    public Event getEventById(long eventId) {
        return events.get(eventId);
    }


    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum){
        return events.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());
    }


    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum){
        return events.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event){
        Event newEvent = new EventDAO(nextId,event);
        events.put(newEvent.getId(), event);
        nextId++;
        return newEvent;
    }

    //TODO empty id proof
    public Event updateEvent(Event event){
        events.put(event.getId(),event);
        return event;
    }


   public boolean deleteEvent(long eventId){
        return events.remove(eventId) != null;
    }
}
