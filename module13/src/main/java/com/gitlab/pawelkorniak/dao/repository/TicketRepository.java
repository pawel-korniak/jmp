package com.gitlab.pawelkorniak.dao.repository;

import com.gitlab.pawelkorniak.dao.GenericInMemoryRepository;
import com.gitlab.pawelkorniak.dao.TicketDAO;
import com.gitlab.pawelkorniak.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.Random;

public class TicketRepository extends GenericInMemoryRepository<Ticket, Long> implements TicketDAO {

    @Override
    protected Long generatePK() {
        return Math.abs(new Random().nextLong(100));
    }

    @Override
    protected void updatePK(Ticket event, Long id) {
        event.setId(id);
    }

    @Override
    protected Long getPk(Ticket event) {
        return event.getId();
    }
}

