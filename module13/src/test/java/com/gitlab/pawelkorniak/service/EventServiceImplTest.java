package com.gitlab.pawelkorniak.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import com.gitlab.pawelkorniak.dao.entity.EventEntity;
import com.gitlab.pawelkorniak.dao.EventDAO;
import com.gitlab.pawelkorniak.dao.repository.EventRepository;
import com.gitlab.pawelkorniak.model.Event;
import java.util.Date;
import org.testng.annotations.Test;

public class EventServiceImplTest {

    EventDAO eventDAO = new EventRepository();

    @Test
    public void testGetEventById() {
    }

    @Test
    void getEventById() {
        Event event = new EventEntity();
        event.setId(2);
        event.setDate(new Date());
        event.setTitle("title_1");
        Long pk = eventDAO.create(event);
        Event e2 = eventDAO.read(pk);
        assertEquals( event.getTitle(), e2.getTitle());
        assertEquals( event.getDate().getTime(), e2.getDate().getTime());
    }

    @Test
    void deleteEvent() {
        Event event = new EventEntity();
        event.setId(2);
        event.setDate(new Date());
        event.setTitle("title_1");
        Long pk = eventDAO.create(event);
        assertEquals(event, eventDAO.read(pk));

        eventDAO.delete(event);
        Event e2 = eventDAO.read(pk);
        assertNull(e2);
    }
}