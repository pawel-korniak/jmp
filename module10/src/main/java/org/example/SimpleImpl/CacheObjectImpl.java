package org.example.SimpleImpl;

import org.example.service.CacheObject;

import java.time.LocalTime;

public class CacheObjectImpl implements CacheObject {

    private String key;

    public CacheObjectImpl(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
