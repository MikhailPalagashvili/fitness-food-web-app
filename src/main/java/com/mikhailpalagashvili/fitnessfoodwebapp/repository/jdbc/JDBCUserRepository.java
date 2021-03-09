package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;
import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserBodyWeightInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserRepository;
import com.mikhailpalagashvili.fitnessfoodwebapp.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;

@Repository
public class JDBCUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCUserRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User insertUser(final User user) throws DataAccessException {
        long userId = saveUser(user);
        user.setUserId(userId);
        saveUserBodyWeightInfoToUser(user.getUserBodyWeightInfo(), userId);
        return user;
    }

//    @Override
//    public int insertUser(User user) throws DataAccessException {
//        String sql = "INSERT INTO user VALUES (?,?)";
//        Object[] args = {user.getCreatedAt(), user.getRegisterFormId()};
//        return jdbcTemplate.update(sql, args);
//    }

    @Override
    public int updateUser(List<User> userList) {
        String sql = "update user set ";
        return 0;
    }

    @Override
    public User selectUser(Long userId) throws DataAccessException {
        User user;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USER WHERE userId = ?", this::mapRowToUser, userId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
        return user;
    }

    private User mapRowToUser(ResultSet resultSet, int row) throws SQLException {
        return new UserRowMapper().mapRow(resultSet, row);
    }

    @Override
    public List<User> selectAll() throws DataAccessException {
        List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT * FROM user");
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            User user = new User();
            user.setUserId((Long) map.get("user_id"));
            user.setCreatedAt((Date) map.get("created_at"));
            user.setRegisterFormId((Long) map.get("register_form_id"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public int deleteUser(Long userId) throws DataAccessException {
        return 0;
    }

    @Override
    public int count() throws DataAccessException {
        int res;
        try {
            res = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
        } catch (NullPointerException nullPointerException) {
            return 0;
        }
        return res;
    }

    private long saveUser(final User user) {
        user.setCreatedAt(new Date());
        final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreatorFactory(
                "insert into user (createdAt) values (?)", Types.TIMESTAMP
        ).newPreparedStatementCreator(Collections.singletonList(
                new Timestamp(user.getCreatedAt().getTime())));
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveUserBodyWeightInfoToUser(final UserBodyWeightInfo userBodyWeightInfo, final long userId) {
        jdbcTemplate.update("insert into user_body_weight_info (userId, weight, height, bodyFatPercentage, dietPlan, numberofExercisingDays)" +
                        " values (?, ?, ?, ?, ?, ?)", userId, userBodyWeightInfo.getWeight(), userBodyWeightInfo.getHeight(),
                userBodyWeightInfo.getBodyFatPercentage(), userBodyWeightInfo.getDietPlan(), userBodyWeightInfo.getNumberOfExercisingDays());
    }
}

