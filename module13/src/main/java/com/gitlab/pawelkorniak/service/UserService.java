package com.gitlab.pawelkorniak.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitlab.pawelkorniak.config.Storage;
import com.gitlab.pawelkorniak.dao.UserDAO;
import com.gitlab.pawelkorniak.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private static long nextId;
    private static final String prefix = "user:";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Storage users;


    public User getUserById(long userId){
        try {
            return objectMapper.readValue(users.get(prefix + userId),User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO nullProof
    public User getUserByEmail(String email){
        return users.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value, User.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }


    public List<User> getUsersByName(String name, int pageSize, int pageNum){
        return users.query(prefix)
                .map(value -> {
                    try {
                        return objectMapper.readValue(value,User.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }

    public User createUser(User user){
        User newUser = new UserDAO(nextId,user);
        try {
            users.put(prefix + nextId, objectMapper.writeValueAsString(newUser));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        nextId++;
        return newUser;
    }

    public User updateUser(User user){
        return createUser(user);
    }

    public boolean deleteUser(long userId){
        return users.remove(prefix + userId);
    }

}
