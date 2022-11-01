package com.gitlab.pawelkorniak.dao;

import com.gitlab.pawelkorniak.model.Event;

import java.util.Date;

public class EventDAO implements Event {

    private long id;
    private String title;
    private Date date;

    public EventDAO(long id, Event event) {
        this.id = id;
        this.date = event.getDate();
        this.title = event.getTitle();
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
