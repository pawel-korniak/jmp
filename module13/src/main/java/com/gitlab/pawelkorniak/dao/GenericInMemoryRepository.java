package com.gitlab.pawelkorniak.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericInMemoryRepository<T, PK extends Serializable> implements GenericDAO<T, PK> {

    protected Map<PK, T> storage = new HashMap<>();

    protected abstract PK generatePK();
    protected abstract void updatePK(T t, PK pk);

    protected abstract PK getPk(T t);

    @Override
    public PK create(T newInstance) {
        PK id = generatePK();
        updatePK(newInstance, id);
        storage.put(id, newInstance);
        return id;
    }

    @Override
    public T read(PK id) {
        return storage.get(id);
    }

    @Override
    public void update(T transientObject) {
        throw new UnsupportedOperationException("Not implemented yes :(");

    }

    @Override
    public void delete(T persistentObject) {
        storage.remove(getPk(persistentObject));
    }
}
