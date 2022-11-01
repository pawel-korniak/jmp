package com.gitlab.pawelkorniak.dao;

import com.gitlab.pawelkorniak.model.Ticket;

public class TicketDAO implements Ticket {

    private long id;
    private int place;
    private Category category;
    private long eventId;
    private long userId;

    public TicketDAO(long userId, long eventId, int place, Category category) {

        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }
}
