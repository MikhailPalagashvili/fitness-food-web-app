package com.mikhailpalagashvili.fitnessfoodwebapp.repository;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;

import java.util.List;

public interface UserRepository {
    User insertOne(User user);

    int updateUser(List<User> userList);
}
