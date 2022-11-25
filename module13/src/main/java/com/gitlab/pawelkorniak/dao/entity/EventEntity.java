package com.gitlab.pawelkorniak.dao.entity;

import com.gitlab.pawelkorniak.model.Event;

import java.util.Date;
import java.util.Random;

public class EventEntity implements Event {

    private long id;
    private String title;
    private Date date;

    public EventEntity() {
    }

    public EventEntity(long id, Event event) {
        this.id = id;
        this.date = event.getDate();
        this.title = event.getTitle();
    }

    public EventEntity(long eventId) {
        this.id = eventId;
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }
}
