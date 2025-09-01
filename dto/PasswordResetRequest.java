package com.example.gmt_ideafev.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {
    private String username;
    private String findpassword;
    private String newpassword;
}
