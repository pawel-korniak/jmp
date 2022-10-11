package org.example.SimpleImpl;

import org.example.service.CacheObject;
import org.example.service.CacheService;
import org.example.service.RemovalListener;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CacheServiceImpl implements CacheService {

    Logger logger = Logger.getLogger(CacheServiceImpl.class.getName());
    static final int MAX_SIZE = 100000;
    RemovalListener removalListener = new RemovalListenerImpl();
    Map<String,CacheObject> cacheEntries = new HashMap<>();
    Map<String,Integer> entriesCounters = new HashMap<>();
    @Override
    public CacheObject get(String key) {

        CacheObject cacheObject = cacheEntries.get(key);
        if (cacheObject != null){
            entriesCounters.put(key,entriesCounters.get(key) + 1);
        }

        return cacheObject;
    }

    @Override
    public void put(CacheObject object) {
        if (cacheEntries.size() >= MAX_SIZE){
            applyStrategy();
        }
        if (!cacheEntries.containsKey(object.getKey())){
            cacheEntries.put(object.getKey(), object);
            entriesCounters.put(object.getKey(), 0);
        }
//        logger.info("Object" +
//                object.getKey() +
//                " already exist");
    }

    private void applyStrategy() {
        var key = entriesCounters.entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                })
                .findFirst()
                .map(entry -> entry.getKey())
                .get();
        logger.info("Removing object with key: " + key +
                " and counter: " + entriesCounters.get(key)
                );
        entriesCounters.remove(key);
        cacheEntries.remove(key);
    }
}
