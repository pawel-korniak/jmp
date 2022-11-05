package com.gitlab.pawelkorniak.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitlab.pawelkorniak.config.Storage;
import com.gitlab.pawelkorniak.dao.entity.TicketEntity;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TicketService {

    private static long nextId;
    private static final String prefix = "ticket:";
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    Storage tickets;

    Logger logger = Logger.getLogger(TicketService.class.getName());

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category){
        Ticket newTicket = new TicketEntity(userId, eventId, place, category);
        try {
            tickets.put(prefix + nextId, objectMapper.writeValueAsString(newTicket));
            logger.info("Ticket Created: " + newTicket);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return newTicket;
    }

    public List<Ticket> getBookedTickets(User user){
        return tickets.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value,Ticket.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(ticket -> ticket.getUserId() == user.getId())
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event){
        return tickets.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value,Ticket.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(ticket -> ticket.getEventId() == event.getId())
                .collect(Collectors.toList());
    }

    public boolean cancelTicket(long ticketId){
        logger.info("Ticket Canceled: " + ticketId);
        return tickets.remove(prefix + ticketId) ;
    }
}
