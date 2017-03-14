package com.github.aelmod.simple_social_network.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


public class Conf {
    private RequestMappingHandlerAdapter adapter;

    @Autowired
    public Conf(RequestMappingHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    @PostConstruct
    public void prioritizeCustomArgumentMethodHandlers() {
        List<HandlerMethodArgumentResolver> argumentResolvers =
                new ArrayList<>(adapter.getArgumentResolvers());
        List<HandlerMethodArgumentResolver> customResolvers =
                adapter.getCustomArgumentResolvers();
        argumentResolvers.removeAll(customResolvers);
        argumentResolvers.addAll(0, customResolvers);
        adapter.setArgumentResolvers(argumentResolvers);
    }
}
