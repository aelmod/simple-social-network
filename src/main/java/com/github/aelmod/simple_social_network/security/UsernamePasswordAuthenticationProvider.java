package com.github.aelmod.simple_social_network.security;

import com.github.aelmod.simple_social_network.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (Objects.equals(authentication.getPrincipal(), "aelmod") && Objects.equals(authentication.getCredentials(), "1234")) {
            User user = new User();
            user.setName("aelmod");
            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), new ArrayList<>());
        }
        throw new BadCredentialsException("Pidoras");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
