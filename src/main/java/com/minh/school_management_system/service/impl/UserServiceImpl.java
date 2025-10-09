package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.entity.User;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = getCurrentUser();

        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateEmail(String newEmail) {
        User user = getCurrentUser();

        if(userRepository.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("This email is already in use");
        }
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find the user by username from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        // 2. Convert user's role into a GrantedAuthority list
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRole().getName().toString()));

        // 3. Return a UserDetails object recognized by Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),         // username
                user.getPassword(),         // encoded password
                user.isActive(),            // enabled status
                true,                       // accountNonExpired
                true,                       // credentialsNonExpired
                true,                       // accountNonLocked
                authorities                 // user's authorities (roles)
        );
    }

}
