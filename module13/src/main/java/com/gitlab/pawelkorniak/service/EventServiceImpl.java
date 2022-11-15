package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.EventEntity;
import com.gitlab.pawelkorniak.dao.repository.EventDAO;
import com.gitlab.pawelkorniak.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService{
    @Autowired
    private EventDAO events;

    Logger logger = Logger.getLogger(EventServiceImpl.class.getName());

    public Event getEventById(long eventId){
            return events.read(eventId);
    }


    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum){
        return events.readAll().stream()
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());
    }


    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum){
        return events.readAll().stream()
                .filter(event -> event.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event){
        events.create(event);
        logger.info("Event created: " + event);
        return event;
    }

    //TODO empty id proof
    public Event updateEvent(Event event){
        return createEvent(event);
    }


   public boolean deleteEvent(long eventId){
        logger.info("Event deleted: " + eventId);
       events.delete(new EventEntity(eventId));
        return true;
    }
}
