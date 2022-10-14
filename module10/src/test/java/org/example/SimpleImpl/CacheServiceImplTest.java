package org.example.SimpleImpl;

import net.datafaker.Faker;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.*;

public class CacheServiceImplTest {

    Faker faker = new Faker();

    @BeforeTest
    public void setUp() {
        for (int i = 0; i < cacheService.MAX_SIZE; i++) {
            String key = faker.name().fullName() + i;
            cacheService.put(new CacheObjectImpl(key));
        }
    }

    CacheServiceImpl cacheService = new CacheServiceImpl();

    @Test
    public void max_size_test() {
        int maxSize = cacheService.cacheEntries.size();
        cacheService.put(new CacheObjectImpl(faker.name().fullName()));
        int maxSizeAfterPut = cacheService.cacheEntries.size();
        assertTrue(maxSize == maxSizeAfterPut, "Max size error");

    }

    @Test
    public void strategy_test() {
        String fullName = faker.name().fullName();
        cacheService.put(new CacheObjectImpl(fullName));
        cacheService.cacheEntries.forEach((k, v) -> {
            if (!Objects.equals(k, fullName)) cacheService.get(k);// make entry for each objects except fullName
        });
        cacheService.put(new CacheObjectImpl(faker.name().fullName()));
        assertNull(cacheService.get(fullName));
    }

    @AfterClass
    public void showStatistics() {
        cacheService.getStatistics();
    }
}