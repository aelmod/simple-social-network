package com.github.aelmod.simple_social_network.security;

import com.github.aelmod.simple_social_network.model.User;
import com.github.aelmod.simple_social_network.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final TestRepository testRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(TestRepository testRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.testRepository = testRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User userByUsername = testRepository.getUserByUsername(authentication.getPrincipal().toString());
        if (Objects.equals(authentication.getPrincipal(), userByUsername.getNickname())
                && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userByUsername.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userByUsername, authentication.getCredentials(), new ArrayList<>());
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
