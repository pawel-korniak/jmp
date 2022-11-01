package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.UserDAO;
import com.gitlab.pawelkorniak.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private static long nextId;
    Map<Long, User> users = new HashMap<>();


    public User getUserById(long userId){
        return users.get(userId);
    }

    //TODO nullProof
    public User getUserByEmail(String email){
        return users.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }


    public List<User> getUsersByName(String name, int pageSize, int pageNum){
        return users.entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }

    public User createUser(User user){
        User newUser = new UserDAO(nextId,user);
        users.put(nextId,newUser);
        nextId++;
        return newUser;
    }

    public User updateUser(User user){
        users.put(user.getId(), user);
        return user;
    }

    public boolean deleteUser(long userId){
        return users.remove(userId) != null;
    }

}
