package com.github.pawelkorniak.module33.impl;


import com.github.pawelkorniak.module33.api.EventService;
import com.github.pawelkorniak.module33.dto.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventServiceImpl implements EventService {

    List<Event> events = new ArrayList<>();

    @Override
    public Event createEvent(Event event) {
        return events.add(event) ? event : null;
    }

    @Override
    public Event updateEvent(Event event) {
        return events.add(event) ? event : null;
    }

    @Override
    public Event getEvent(long id) {
        return events.stream().filter(event ->  event.getId()==id).findFirst().get();
    }

    @Override
    public void deleteEvent(long id) {
        events.remove(getEvent(id));
    }

    @Override
    public List<Event> getAllEvents() {
        return events.stream().toList();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return events.stream().filter(event -> event.getTitle().equals(title)).collect(Collectors.toList());
    }
}

