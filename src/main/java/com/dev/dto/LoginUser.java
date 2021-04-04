package com.dev.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {

    private String userName;
    private String password;
    private List<String> roles;
}
