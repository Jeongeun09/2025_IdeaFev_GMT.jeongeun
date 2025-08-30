package com.example.gmt_ideafev.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String findpassword;
    private String email;
    private String password;
    private String username;
    private String requester;
}
