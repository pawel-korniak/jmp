package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.TicketEntity;
import com.gitlab.pawelkorniak.dao.entity.UserEntity;
import com.gitlab.pawelkorniak.dao.repository.TicketDAO;
import com.gitlab.pawelkorniak.dao.repository.TicketRepository;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TicketServiceImplTest {
    TicketDAO ticketDAO = new TicketRepository();

    @Test
    public void testGetEventById() {
    }

    @Test
    void getEventById() {
        Ticket ticket = new TicketEntity();
        ticket.setId(2);
        ticket.setCategory(Ticket.Category.BAR);
        ticket.setEventId(12);
        ticket.setPlace(1);
        ticket.setUserId(2);
        Long pk = ticketDAO.create(ticket);
        Ticket e2 = ticketDAO.read(pk);
        assertEquals( ticket.getEventId(), e2.getEventId());
        assertEquals( ticket.getCategory(), e2.getCategory());
        assertEquals( ticket.getPlace(), e2.getPlace());
        assertEquals( ticket.getUserId(), e2.getUserId());
    }

    @Test
    void deleteEvent() {
        Ticket ticket = new TicketEntity();
        ticket.setId(2);
        ticket.setCategory(Ticket.Category.BAR);
        ticket.setEventId(12);
        ticket.setPlace(1);
        ticket.setUserId(2);
        Long pk = ticketDAO.create(ticket);
        assertEquals(ticket, ticketDAO.read(pk));

        ticketDAO.delete(ticket);
        Ticket e2 = ticketDAO.read(pk);
        assertNull(e2);
    }
}