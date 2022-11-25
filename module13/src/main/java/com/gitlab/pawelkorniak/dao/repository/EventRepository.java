package com.gitlab.pawelkorniak.dao.repository;

import com.gitlab.pawelkorniak.dao.EventDAO;
import com.gitlab.pawelkorniak.dao.GenericInMemoryRepository;
import com.gitlab.pawelkorniak.model.Event;
import java.util.Random;
import org.springframework.stereotype.Component;

public class EventRepository extends GenericInMemoryRepository<Event, Long> implements EventDAO {

    @Override
    protected Long generatePK() {
        return Math.abs(new Random().nextLong(100));
    }

    @Override
    protected void updatePK(Event event, Long id) {
        event.setId(id);
    }

    @Override
    protected Long getPk(Event event) {
        return event.getId();
    }
}

