package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.TicketEntity;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface TicketService{
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    public List<Ticket> getBookedTickets(User user);

    public List<Ticket> getBookedTickets(Event event);

    public boolean cancelTicket(long ticketId);
}
