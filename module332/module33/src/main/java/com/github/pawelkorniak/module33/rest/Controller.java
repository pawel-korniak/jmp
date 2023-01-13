package com.github.pawelkorniak.module33.rest;

import com.github.pawelkorniak.module33.api.EventService;
import com.github.pawelkorniak.module33.dto.Event;
import com.github.pawelkorniak.module33.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    public EventService eventService;

    @GetMapping("/events/{id}")
    public Event getById(@PathVariable long id){
        return eventService.getEvent(id);
    }

    @GetMapping("events/")
    public List<Event> getAll(){
        return eventService.getAllEvents();
    }

    @PostMapping("/events")
    public void saveEvent(@RequestBody Event event){
        eventService.createEvent(event);
    }

    @PutMapping("events/{id}")
    public void updateEvent(@PathVariable long id){
        eventService.updateEvent(eventService.getEvent(id));
    }

    @DeleteMapping("events/{id}")
    public void deleteEvent(@PathVariable long id){
        eventService.deleteEvent(id);
    }
}
