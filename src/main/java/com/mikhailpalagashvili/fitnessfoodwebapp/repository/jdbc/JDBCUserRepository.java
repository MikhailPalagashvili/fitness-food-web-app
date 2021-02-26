package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;
import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserBodyWeightInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JDBCUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User insertOne(User user) {
        long userId = saveUserInfo(user);
        user.setUserId(userId);
        saveUserBodyWeightInfoToUser(user.getUserBodyWeightInfo(), userId);
        return user;
    }

    @Override
    public int updateUser(List<User> userList) {
        String sql = "update user set "
    }

    private long saveUserInfo(User user) {
        user.setCreatedAt(new Date());
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreatorFactory(
                "insert into user (email, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(user.getRegisterForm().getUserLoginInfo().getEmail(),
                new Timestamp(user.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveUserBodyWeightInfoToUser(UserBodyWeightInfo userBodyWeightInfo, long userId) {
        jdbcTemplate.update("insert into user_body_weight_info (userId, weight, height, bodyFatPercentage, dietPlan, numberofExercisingDays)" +
                        " values (?, ?, ?, ?, ?, ?)", userId, userBodyWeightInfo.getWeight(), userBodyWeightInfo.getHeight(),
                userBodyWeightInfo.getBodyFatPercentage(), userBodyWeightInfo.getDietPlan(), userBodyWeightInfo.getNumberOfExercisingDays());
    }
}

