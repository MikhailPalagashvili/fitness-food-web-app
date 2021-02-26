package com.mikhailpalagashvili.fitnessfoodwebapp.service;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserInfoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public boolean insert(UserLoginInfo userLoginInfo) {
        return userInfoRepository.insertOne(userLoginInfo) > 0;
    }

    public int count() {
        return userInfoRepository.count();
    }

    public List<UserLoginInfo> selectAll() {
        return userInfoRepository.selectAll();
    }

    public UserLoginInfo selectOne(String email) {
        return userInfoRepository.selectOne(email);
    }
}
