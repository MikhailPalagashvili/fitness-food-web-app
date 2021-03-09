package com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.RegisterFormRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JDBCRegisterFormRepository implements RegisterFormRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCRegisterFormRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insert(final RegisterForm registerForm) {
        String sql = "INSERT INTO register_form VALUES (?,?)";
        Object[] args = {registerForm.getUserName(), registerForm.getPhoneNumber()};
        int rowNumber = jdbcTemplate.update(sql, args);
        System.out.println("Number of row inserted " + rowNumber);
        return rowNumber;
    }
}
