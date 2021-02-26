package com.mikhailpalagashvili.fitnessfoodwebapp.rowmapper;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginInfoRowMapper implements RowMapper<UserLoginInfo> {
    @Override
    public UserLoginInfo mapRow(ResultSet resultSet, int row) throws SQLException {
        UserLoginInfo newUserInfoLogin = new UserLoginInfo();
        newUserInfoLogin.setEmail(resultSet.getString("email"));
        newUserInfoLogin.setPassword(resultSet.getString("password"));
        System.out.println("testing map row user login info method.....");
        return newUserInfoLogin;
    }
}
