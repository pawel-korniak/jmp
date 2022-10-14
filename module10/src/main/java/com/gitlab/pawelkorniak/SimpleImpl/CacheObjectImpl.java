package com.gitlab.pawelkorniak.SimpleImpl;

import com.gitlab.pawelkorniak.service.CacheObject;

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
