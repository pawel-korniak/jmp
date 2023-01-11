package com.github.pawelkorniak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pawelkorniak.Event;

@RestController
public class Controller {

    @Autowired
    EventServiceImpl eventService;

    @GetMapping("events/{id}")
    public Event getById(@PathVariable long id){
        return eventService.getEvent(id);
    }

    @PostMapping("events")
    public void saveEvent(@RequestBody Event event){
        eventService.createEvent(event);
    }

}
