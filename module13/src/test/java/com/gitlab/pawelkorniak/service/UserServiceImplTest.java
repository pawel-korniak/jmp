package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.EventEntity;
import com.gitlab.pawelkorniak.dao.entity.UserEntity;
import com.gitlab.pawelkorniak.dao.repository.EventRepository;
import com.gitlab.pawelkorniak.dao.repository.UserDAO;
import com.gitlab.pawelkorniak.dao.repository.UserRepository;
import com.gitlab.pawelkorniak.model.Event;
import com.gitlab.pawelkorniak.model.User;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    UserDAO userDAO = new UserRepository();

    @Test
    public void testGetEventById() {
    }

    @Test
    void getEventById() {
        User user = new UserEntity();
        user.setId(2);
        user.setEmail("email_1");
        user.setName("name_1");
        Long pk = userDAO.create(user);
        User e2 = userDAO.read(pk);
        assertEquals( user.getName(), e2.getName());
        assertEquals( user.getEmail(), e2.getEmail());
    }

    @Test
    void deleteEvent() {
        User user = new UserEntity();
        user.setId(2);
        user.setEmail("email_1");
        user.setName("name_1");
        Long pk = userDAO.create(user);
        assertEquals(user, userDAO.read(pk));

        userDAO.delete(user);
        User e2 = userDAO.read(pk);
        assertNull(e2);
    }
}