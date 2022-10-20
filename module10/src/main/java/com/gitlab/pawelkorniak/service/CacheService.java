package com.gitlab.pawelkorniak.service;

import java.util.concurrent.ExecutionException;

public interface CacheService<K,V> {

    V get(K key) throws ExecutionException;
    void put(V object);
    void logStatistics();
}
