package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.UserEntity;
import com.gitlab.pawelkorniak.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    public User getUserById(long userId);

    public User getUserByEmail(String email);


    public List<User> getUsersByName(String name, int pageSize, int pageNum);

    public User createUser(User user);


    public User updateUser(User user);

    public boolean deleteUser(long userId);

}
