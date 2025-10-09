package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
