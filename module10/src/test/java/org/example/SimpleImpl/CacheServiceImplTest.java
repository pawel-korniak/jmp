package org.example.SimpleImpl;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.*;

public class CacheServiceImplTest {

    @BeforeTest
    public void setUp(){
        for (int i = 0; i < cacheService.MAX_SIZE; i++) {
            cacheService.put(new CacheObjectImpl("object" + i));
        }
    }

    CacheServiceImpl cacheService = new CacheServiceImpl();

    @Test
    public void max_size_test(){
        int maxSize = cacheService.cacheEntries.size();
        cacheService.put(new CacheObjectImpl("objectA"));
        int maxSizeAfterPut = cacheService.cacheEntries.size();
        assertTrue(maxSize == maxSizeAfterPut, "Max size error");

    }

    @Test
    public void strategy_test(){
        cacheService.cacheEntries.forEach((k,v) -> {
            if(!Objects.equals(k, "object10")) cacheService.get(k);// make entry for each objects except 'object10'
        });
        cacheService.put(new CacheObjectImpl("objectB"));
        assertTrue(cacheService.get("object10") == null, "Strategy error");
    }
}