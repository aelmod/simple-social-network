package com.github.aelmod.simple_social_network.service;

import com.github.aelmod.simple_social_network.model.User;
import com.github.aelmod.simple_social_network.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final TestRepository testRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(TestRepository testRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.testRepository = testRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        testRepository.saveUser(user);
    }
}
