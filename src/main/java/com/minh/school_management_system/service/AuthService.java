package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.LoginRequest;

public interface AuthService {

    public String login(LoginRequest request);

}
