package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserLoginRepository;
import com.mikhailpalagashvili.fitnessfoodwebapp.rowmapper.UserLoginRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCUserLoginRepository implements UserLoginRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCUserLoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertOne(UserLoginInfo userLoginInfo) throws DataAccessException {
        return jdbcTemplate.update("insert into user_login (email, password) values (?, ?)",
                userLoginInfo.getEmail(),
                userLoginInfo.getPassword());
    }



    @Override
    public UserLoginInfo selectOne(String email) throws DataAccessException {
        UserLoginInfo userLoginInfo;
        try {
            userLoginInfo = jdbcTemplate.queryForObject("select * from user_login where email = ?", this::mapRowToUserName, email);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
        return userLoginInfo;
    }

    @Override
    public int updatePassword(UserLoginInfo userLoginInfo) throws DataAccessException {
        String sql = "update user_login set password = ? where email = ?";
        Object[] args = {userLoginInfo.getPassword(), userLoginInfo.getEmail()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteUserLoginInfo(String email) throws DataAccessException {
        String sql = "delete from user_login where email = ?";
        return jdbcTemplate.update(sql, email);
    }

    private UserLoginInfo mapRowToUserName(ResultSet resultSet, int rowNumber) throws SQLException {
        return new UserLoginRowMapper().mapRow(resultSet, rowNumber);
    }

    public void cleanup() {
        String sql = "truncate table user_login";
        jdbcTemplate.update(sql);
        System.out.println("student table cleaned up...");
    }
}
