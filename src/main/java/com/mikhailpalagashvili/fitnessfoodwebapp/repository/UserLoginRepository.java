package com.mikhailpalagashvili.fitnessfoodwebapp.repository;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserLoginRepository {


    // Insert 1 record in user table.
    int insertOne(UserLoginInfo userLoginInfo) throws DataAccessException;

    // Get 1 record of user table.
    UserLoginInfo selectOne(String email) throws DataAccessException;

    // Update 1 record in user table.
    int updatePassword(UserLoginInfo userLoginInfo) throws DataAccessException;

    int deleteUserLoginInfo(String email) throws DataAccessException;
}
