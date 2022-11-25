package com.gitlab.pawelkorniak.dao.repository;

import com.gitlab.pawelkorniak.dao.GenericInMemoryRepository;
import com.gitlab.pawelkorniak.dao.UserDAO;
import com.gitlab.pawelkorniak.model.User;
import org.springframework.stereotype.Component;

import java.util.Random;

public class UserRepository extends GenericInMemoryRepository<User, Long> implements UserDAO {

    @Override
    protected Long generatePK() {
        return Math.abs(new Random().nextLong(100));
    }

    @Override
    protected void updatePK(User event, Long id) {
        event.setId(id);
    }

    @Override
    protected Long getPk(User event) {
        return event.getId();
    }
}

