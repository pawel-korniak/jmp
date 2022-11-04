package com.gitlab.pawelkorniak.facade;

import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.Ticket;
import com.gitlab.pawelkorniak.model.User;
import com.gitlab.pawelkorniak.service.EventService;
import com.gitlab.pawelkorniak.service.TicketService;
import com.gitlab.pawelkorniak.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingFacadeImpl implements BookingFacade{

    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    public BookingFacadeImpl(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }


    @Override
    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventsForDay(day,pageSize,pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name,pageSize,pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketService.bookTicket(userId,eventId,place,category);
    }

    //TODO pagination
    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user).stream()
                .sorted((ticket1, ticket2) -> extractDateFromTicket(ticket1).compareTo(extractDateFromTicket(ticket2)))
                .collect(Collectors.toList());
    }

    //TODO pagination
    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event).stream()
                .sorted((ticket1, ticket2) -> extractUserFromTicket(ticket2).compareTo(extractUserFromTicket(ticket1)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }

    private String extractUserFromTicket(Ticket ticket) {
        return userService.getUserById(ticket.getEventId()).getName();
    }
    private Date extractDateFromTicket(Ticket ticket) {
        return eventService.getEventById(ticket.getEventId()).getDate();
    }
}
