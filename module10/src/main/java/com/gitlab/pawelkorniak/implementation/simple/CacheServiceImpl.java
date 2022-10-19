package com.gitlab.pawelkorniak.implementation.simple;

import com.gitlab.pawelkorniak.service.CacheService;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CacheServiceImpl implements CacheService<String,String> {

    static final int MAX_SIZE = 100_000;

    Logger logger = Logger.getLogger(CacheServiceImpl.class.getName());
    Map<String,String> cacheEntries = new HashMap<>();
    Map<String,Integer> entriesCounters = new HashMap<>();

    private long totalCacheEvictions;
    private long totalTimeMs;
    private long totalPuts;

    @Override
    public String get(String key) {

        String cacheObject = cacheEntries.get(key);
        if (cacheObject != null){
            entriesCounters.put(key,entriesCounters.get(key) + 1);
        }

        return cacheObject;
    }

    @Override
    public void put(String object) {
        var start = LocalTime.now().toNanoOfDay();
        if (cacheEntries.size() >= MAX_SIZE){
            applyStrategy();
        }
        if (!cacheEntries.containsKey(object)){
            cacheEntries.put(object, object);
            entriesCounters.put(object, 0);
        }
        totalTimeMs += LocalTime.now().toNanoOfDay() - start;
        totalPuts++;
    }

    @Override
    public void logStatistics() {
        logger.info("Statistics:\nTotal evictions: " + totalCacheEvictions +
                "\nAverage time spent for putting values: " + (totalTimeMs/totalPuts) +
                " nanoseconds"
                );
    }

    private void applyStrategy() {
        var key = entriesCounters.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .findFirst()
                .map(entry -> entry.getKey())
                .get();
        logger.info("Removing object with key: " + key +
                " and counter: " + entriesCounters.get(key)
                );
        entriesCounters.remove(key);
        cacheEntries.remove(key);
        totalCacheEvictions++;
    }
}
