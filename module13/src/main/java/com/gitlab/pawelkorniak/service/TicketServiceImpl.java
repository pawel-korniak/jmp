package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.TicketEntity;
import com.gitlab.pawelkorniak.dao.repository.TicketDAO;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketDAO tickets;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(TicketServiceImpl.class.getName());

    public TicketServiceImpl() {
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket newTicket = new TicketEntity(userId, eventId, place, category);
        tickets.create(newTicket);
        logger.info("Ticket Created: " + newTicket);
        return newTicket;
    }

    public List<Ticket> getBookedTickets(User user) {
        return tickets.readAll().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .sorted((ticket1, ticket2) -> extractDateFromTicket(ticket1).compareTo(extractDateFromTicket(ticket2)))
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event) {
        return tickets.readAll().stream()
                .filter(ticket -> ticket.getEventId() == event.getId())
                .sorted((ticket1, ticket2) -> extractUserFromTicket(ticket2).compareTo(extractUserFromTicket(ticket1)))
                .collect(Collectors.toList());
    }

    public boolean cancelTicket(long ticketId) {
        tickets.delete(new TicketEntity(ticketId));
        logger.info("Ticket Canceled: " + ticketId);
        return true;
    }

    private String extractUserFromTicket(Ticket ticket) {
        return userService.getUserById(ticket.getEventId()).getName();
    }
    private Date extractDateFromTicket(Ticket ticket) {
        return eventService.getEventById(ticket.getEventId()).getDate();
    }
}
