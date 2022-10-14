package org.example.GuavaImpl;

import com.google.common.cache.*;
import org.example.SimpleImpl.CacheObjectImpl;
import org.example.service.CacheObject;
import org.example.service.CacheService;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CacheServiceGuava implements CacheService {

    public static final int MAXIMUM_SIZE = 100000;
    public static final int EXPIRATION_TIME = 5;

    Logger logger = Logger.getLogger(CacheServiceGuava.class.getName());
    CacheLoader<String, CacheObject> loader = new CacheLoader<>() {
        @Override
        public CacheObject load(String key) {
            return new CacheObjectImpl(key);
        }
    };
    RemovalListener<String, CacheObject> listener = new RemovalListener<>() {
        @Override
        public void onRemoval(RemovalNotification<String, CacheObject> object){
            totalCacheEvictions++;
            logger.info("Removing object with key: " + object.getKey());
        }
    };
    LoadingCache<String, CacheObject> cache = CacheBuilder.newBuilder()
            .maximumSize(MAXIMUM_SIZE)
            .removalListener(listener)
            .expireAfterAccess(EXPIRATION_TIME, TimeUnit.SECONDS)
            .build(loader);


    private long totalCacheEvictions;
    private long totalTimeMs;
    private long totalPuts;

    @Override
    public CacheObject get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(CacheObject object) {
        var start = LocalTime.now().toNanoOfDay();
        cache.put(object.getKey(), object);
        totalPuts++;
        totalTimeMs += (LocalTime.now().toNanoOfDay() - start);
        totalPuts++;
    }

    @Override
    public void getStatistics() {
        logger.info("Statistics:\nTotal evictions: " + totalCacheEvictions +
                "\nAverage time spent for putting values: " + (totalTimeMs/totalPuts) +
                " nanoseconds"
        );
    }
}
