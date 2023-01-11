package com.github.pawelkorniak;
import com.github.pawelkorniak.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pawelkorniak.Event;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService{

    @Autowired
    private JpaService jpaService;
    @Override
    public Event createEvent(Event event) {
        return jpaService.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return jpaService.save(event);
    }

    @Override
    public Event getEvent(long id) {
        return jpaService.findById(id).get();
    }

    @Override
    public void deleteEvent(long id) {
        jpaService.delete(jpaService.findById(id).get());
    }

    @Override
    public List<Event> getAllEvents() {
        return jpaService.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return jpaService.findAll().stream()
                .filter(event -> title.equals(event.getTitle()))
                .collect(Collectors.toList());
    }
}
