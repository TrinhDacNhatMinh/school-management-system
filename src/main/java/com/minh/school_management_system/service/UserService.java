package com.minh.school_management_system.service;

import com.minh.school_management_system.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User getCurrentUser();

    public void updatePassword(Long userId, String oldPassword, String newPassword);

    public void updateEmail(String newEmail);

}
