package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity represents an account in the system.
 * Each user has a username, password, email, active status, and a Role.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Unique username for login
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Password for authentication
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Contact email
     */
    @Column(name = "email", length = 50)
    private String email;

    /**
     * Account status: active or not
     */
    @Column(name = "active", nullable = false)
    private boolean active = true;

    /**
     * Role assigned to this user
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
