package com.example.gmt_ideafev.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse {
    private boolean success;
    private String message;
    private int status;
    private String code;

    public ApiResponse(boolean success, int status, String code, String message) {
        this.success = success;
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
