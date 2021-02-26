package com.mikhailpalagashvili.fitnessfoodwebapp.repository;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserInfoRepository {

    // Get the number of user table.
    int count() throws DataAccessException;

    // Insert 1 record in user table.
    int insertOne(UserLoginInfo userLoginInfo) throws DataAccessException;

    // Get 1 record of user table.
    UserLoginInfo selectOne(String email) throws DataAccessException;

    // Get all data of user table.
    List<UserLoginInfo> selectAll() throws DataAccessException;

    // Update 1 record in user table.
    int updatePassword(UserLoginInfo userLoginInfo) throws DataAccessException;

    // Delete 1 record from user table.
    int deleteUser(String email) throws DataAccessException;
}
