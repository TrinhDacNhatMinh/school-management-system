package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.LoginRequest;
import com.minh.school_management_system.entity.User;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.security.JwtUtil;
import com.minh.school_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!user.isActive()) {
            throw new BadCredentialsException("Account is inactive");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

}
