package com.minh.school_management_system.controller;

import com.minh.school_management_system.entity.User;
import com.minh.school_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        User response = userService.getCurrentUser();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> updatePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        User currentUser = userService.getCurrentUser();
        userService.updatePassword(currentUser.getId(), oldPassword, newPassword);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/email")
    public ResponseEntity<Void> updateEmail(@RequestParam String newEmail) {
        userService.updateEmail(newEmail);
        return ResponseEntity.noContent().build();
    }

}
