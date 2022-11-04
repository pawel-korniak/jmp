package com.gitlab.pawelkorniak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Storage {


    private Map<String, String> storage = new HashMap<>();

    public String get(String id) {
        return storage.get(id);
    }

    public Stream<String> query(String prefix) {
        return storage.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .map(Map.Entry::getValue)
                ;
    }

    public void put(String key, String value) {
        storage.put(key, value);
    }

    public boolean remove(String key) {
        return storage.remove(key) != null;
    }
}
