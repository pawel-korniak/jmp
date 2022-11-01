package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.TicketDAO;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketService {

    private static long nextId;
    Map<Long, Ticket> tickets = new HashMap<>();

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category){
        Ticket newTicket = new TicketDAO(userId, eventId, place, category);
        tickets.put(nextId, newTicket);
        return newTicket;
    }

    public List<Ticket> getBookedTickets(User user){
        return tickets.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(ticket -> ticket.getUserId() == user.getId())
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event){
        return tickets.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(ticket -> ticket.getEventId() == event.getId())
                .collect(Collectors.toList());
    }

    public boolean cancelTicket(long ticketId){
        return tickets.remove(ticketId) != null;
    }
}
