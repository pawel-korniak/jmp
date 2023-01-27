package com.github.pawelkorniak.module33.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.pawelkorniak.module33.api.EventService;
import com.github.pawelkorniak.module33.dto.Event;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EventServiceImpl implements EventService {

    @Autowired
    RestHighLevelClient client;

    List<Event> events = new ArrayList<>();

    @Override
    public Event createEvent(Event event) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        IndexRequest indexRequest = new IndexRequest("events");
        try {
            String json = ow.writeValueAsString(event);
            indexRequest.source(json,XContentType.JSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
            log.info("Indexed Element: " + event + "\nindex : " + response.getIndex() + "\nversion : " + response.getVersion());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return events.add(event) ? event : null;
    }

    @Override
    public Event updateEvent(Event event) {
        return events.add(event) ? event : null;
    }

    @Override
    public Event getEvent(long id) {
        return events.stream().filter(event ->  event.getId()==id).findFirst().get();
    }

    @Override
    public void deleteEvent(long id) {
        events.remove(getEvent(id));
    }

    @Override
    public List<Event> getAllEvents() {
        return events.stream().toList();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return events.stream().filter(event -> event.getTitle().equals(title)).collect(Collectors.toList());
    }
}

