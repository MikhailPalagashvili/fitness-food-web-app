package com.mikhailpalagashvili.fitnessfoodwebapp.service;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService {
    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public boolean insert(UserLoginInfo userLoginInfo) {
        return userLoginRepository.insertOne(userLoginInfo) > 0;
    }

    public UserLoginInfo selectOne(String email) {
        return userLoginRepository.selectOne(email);
    }
}
