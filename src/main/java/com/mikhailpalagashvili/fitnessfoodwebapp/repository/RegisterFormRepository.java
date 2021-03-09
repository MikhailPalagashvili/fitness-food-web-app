package com.mikhailpalagashvili.fitnessfoodwebapp.repository;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import org.springframework.dao.DataAccessException;

public interface RegisterFormRepository {
    int insert(final RegisterForm registerForm) throws DataAccessException;

}
