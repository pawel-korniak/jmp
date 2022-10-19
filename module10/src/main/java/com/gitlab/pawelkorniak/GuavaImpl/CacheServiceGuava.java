package com.gitlab.pawelkorniak.GuavaImpl;

import com.google.common.cache.*;
import com.gitlab.pawelkorniak.service.CacheService;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CacheServiceGuava implements CacheService<String, String> {

    public static final int MAXIMUM_SIZE = 100000;
    public static final int EXPIRATION_TIME = 5;

    Map<String, Long> timeStampsMap = new HashMap<>();

    Logger logger = Logger.getLogger(CacheServiceGuava.class.getName());
    CacheLoader<String, String> loader = new CacheLoader<>() {
        @Override
        public String load(String key) {
            return new String(key.toUpperCase());
        }
    };
    RemovalListener<String, String> listener = new RemovalListener<>() {
        @Override
        public void onRemoval(RemovalNotification<String, String> object) {
            totalCacheEvictions++;
            logger.info("Removing object with key: " + object.getKey());
        }
    };
    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(MAXIMUM_SIZE)
            .removalListener(listener)
            .expireAfterAccess(EXPIRATION_TIME, TimeUnit.SECONDS)
            .build(loader);


    private long totalCacheEvictions;
    private long totalTimeMs;
    private long totalPuts;

    @Override
    public String get(String key) {
        try {
            timeStampsMap.put(key, LocalTime.now().toNanoOfDay());
            return cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(String object) {
        var start = LocalTime.now().toNanoOfDay();
        timeStampsMap.put(object, LocalTime.now().toNanoOfDay());
        if (cache.size() >= MAXIMUM_SIZE) {
            applyLFUStrategy();
        }
        cache.put(object, object);
        totalPuts++;
        totalTimeMs += (LocalTime.now().toNanoOfDay() - start);

    }

    private void applyLFUStrategy() {
        var key = timeStampsMap.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                .findFirst()
                .map(entry -> entry.getKey())
                .get();

        cache.invalidate(key);
    }

    @Override
    public void getStatistics() {
        logger.info("Statistics:\nTotal evictions: " + totalCacheEvictions +
                "\nAverage time spent for putting values: " + (totalTimeMs / totalPuts) +
                " nanoseconds"
        );
    }
}
