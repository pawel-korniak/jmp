package org.example.service;

import java.util.concurrent.ExecutionException;

public interface CacheService {

    CacheObject get(String key) throws ExecutionException;
    void put(CacheObject object);
    void getStatistics();
}
