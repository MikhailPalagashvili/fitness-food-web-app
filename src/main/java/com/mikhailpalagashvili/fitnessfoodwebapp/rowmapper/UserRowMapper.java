package com.mikhailpalagashvili.fitnessfoodwebapp.rowmapper;


import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setCreatedAt(rs.getDate("created_at"));
        user.setRegisterFormId(rs.getLong("register_form_id"));
        return user;
    }
}
