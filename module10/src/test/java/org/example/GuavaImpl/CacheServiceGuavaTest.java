package org.example.GuavaImpl;

import net.datafaker.Faker;
import org.example.SimpleImpl.CacheObjectImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CacheServiceGuavaTest {
    Faker faker = new Faker();
    CacheServiceGuava cacheService = new CacheServiceGuava();

    @Test
    public void shouldPutObject(){
        cacheService.put(new CacheObjectImpl(faker.name().fullName()));
        assertTrue(cacheService.cache.size() == 1);
    }

    @Test
    public void shouldEraseAllAfter5Seconds() throws InterruptedException {
        cacheService.put(new CacheObjectImpl(faker.name().fullName()));
        Thread.sleep(6000);
        cacheService.cache.cleanUp();
        assertTrue(cacheService.cache.size() == 0);
    }

    @AfterClass
    public void showStatistics() {
        cacheService.getStatistics();
    }
}