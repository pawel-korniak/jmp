package com.github.pawelkorniak.module33.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event {

    static long nextId = 0;

    public Event() {
        this.id = nextId;
        nextId++;
    }

    private long id;
    private String title;
    private String place;
    private String speaker;
    private String eventType;
    private LocalDateTime dateTime;
}
