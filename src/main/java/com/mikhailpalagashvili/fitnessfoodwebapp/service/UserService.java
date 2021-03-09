package com.mikhailpalagashvili.fitnessfoodwebapp.service;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.User;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean insert(final User user) {
        return user != null;
    }

    public int count() {
        return userRepository.count();
    }

    public List<User> selectAll() {
        return userRepository.selectAll();
    }

    public User select(Long userId) {return userRepository.selectUser(userId);}

}
