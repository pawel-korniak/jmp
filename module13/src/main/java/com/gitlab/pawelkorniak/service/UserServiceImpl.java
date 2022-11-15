package com.gitlab.pawelkorniak.service;

import com.gitlab.pawelkorniak.dao.entity.UserEntity;
import com.gitlab.pawelkorniak.dao.repository.UserDAO;
import com.gitlab.pawelkorniak.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO users;
@Override

    public User getUserById(long userId){
        return users.read(userId);
    }
    @Override
    public User getUserByEmail(String email){
        return users.readAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum){
        return users.readAll().stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }
    @Override
    public User createUser(User user){
        User newUser = new UserEntity(user);

        users.create(newUser);

        return newUser;
    }
    @Override
    public User updateUser(User user){
        return createUser(user);
    }
    @Override
    public boolean deleteUser(long userId){
        users.delete(new UserEntity(userId));
        return true;
    }

}
