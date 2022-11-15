package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.EventEntity;
import com.gitlab.pawelkorniak.model.Event;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface EventService {
    public Event getEventById(long eventId);

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum);


    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    public Event createEvent(Event event);

    public Event updateEvent(Event event);


    public boolean deleteEvent(long eventId);
}
