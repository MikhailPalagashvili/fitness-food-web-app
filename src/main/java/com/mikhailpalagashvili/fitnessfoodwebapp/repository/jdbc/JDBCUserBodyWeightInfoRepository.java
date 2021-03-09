package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserBodyWeightInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserBodyWeightInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCUserBodyWeightInfoRepository implements UserBodyWeightInfoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCUserBodyWeightInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public UserBodyWeightInfo save(UserBodyWeightInfo userBodyWeightInfo) {
        return null;
    }
}
