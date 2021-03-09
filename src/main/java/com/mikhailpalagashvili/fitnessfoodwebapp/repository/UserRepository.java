package com.mikhailpalagashvili.fitnessfoodwebapp.repository;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRepository {
    User insertUser(User user) throws DataAccessException;

    int updateUser(List<User> userList) throws DataAccessException;

    User selectUser(Long userId) throws DataAccessException;

    List<User> selectAll() throws DataAccessException;

    int deleteUser(Long userId) throws DataAccessException;

    int count() throws DataAccessException;
}
