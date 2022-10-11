package org.example.service;

public interface CacheService {
    CacheObject get(String key);
    void put(CacheObject object);
}
