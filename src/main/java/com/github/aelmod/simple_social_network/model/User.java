package com.github.aelmod.simple_social_network.model;

import lombok.*;

import java.sql.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;
    private Date birthday;
    private String email;
    private int phone;
    private int countryId;
    private int cityId;
    private String address;
}
