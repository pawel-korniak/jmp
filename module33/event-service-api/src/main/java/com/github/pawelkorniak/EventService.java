package com.github.pawelkorniak;
import com.github.pawelkorniak.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Event event);
    Event getEvent(long id);
    void deleteEvent(long id);
    List<Event> getAllEvents();
    List<Event> getAllEventsByTitle(String title);
}
