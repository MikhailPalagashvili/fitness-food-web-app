package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserInfoRepository;
import com.mikhailpalagashvili.fitnessfoodwebapp.rowmapper.UserLoginInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCUserInfoRepository implements UserInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCUserInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() throws DataAccessException {
        int count = jdbcTemplate.queryForObject("select count(*) from user_info", Integer.class);
        return count;
    }

    @Override
    public int insertOne(UserLoginInfo userLoginInfo) throws DataAccessException {
        int rowNumber = jdbcTemplate.update("insert into user_info (email, password) values (?, ?)",
                userLoginInfo.getEmail(),
                userLoginInfo.getPassword());
        return rowNumber;
    }

    @Override
    public UserLoginInfo selectOne(String email) throws DataAccessException {
        return jdbcTemplate.queryForObject("select * from user_info where email = ?", this::mapRowToUserName, email);
    }

    @Override
    public List<UserLoginInfo> selectAll() throws DataAccessException {
        return jdbcTemplate.query("select * from user_info", this::mapRowToUserName);
    }

    @Override
    public int updatePassword(UserLoginInfo userLoginInfo) throws DataAccessException {
        String sql = "update student set password = ? where email = ?";
        Object[] args = {userLoginInfo.getPassword(), userLoginInfo.getEmail()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteUser(String email) throws DataAccessException {
        String sql = "delete from user_info where email = ?";
        return jdbcTemplate.update(sql, email);
    }

    private UserLoginInfo mapRowToUserName(ResultSet resultSet, int rowNumber) throws SQLException {
        return new UserLoginInfoRowMapper().mapRow(resultSet, rowNumber);
    }

    public void cleanup() {
        String sql = "truncate table user_info";
        jdbcTemplate.update(sql);
        System.out.println("student table cleaned up...");
    }
}
