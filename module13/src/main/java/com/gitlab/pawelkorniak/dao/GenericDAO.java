package com.gitlab.pawelkorniak.dao;

import java.io.Serializable;

/**
 * Plz google do not repeat DAO or anything like this
 * https://developer.ibm.com/tutorials/j-genericdao/
 * @param <T>
 * @param <PK>
 */
public interface GenericDAO<T, PK extends Serializable> {
    /** Persist the newInstance object into database */
    PK create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    void update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);

}
