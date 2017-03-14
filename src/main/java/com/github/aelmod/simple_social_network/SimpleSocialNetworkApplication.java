package com.github.aelmod.simple_social_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SimpleSocialNetworkApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SimpleSocialNetworkApplication.class, args);
    }
}
