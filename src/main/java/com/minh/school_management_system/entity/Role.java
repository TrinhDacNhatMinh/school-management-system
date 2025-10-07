package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role entity represents a user role in the system.
 * Each Role can be assigned to multiple users.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name of the role (enum)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleName name;

    /**
     * Enum for predefined role names
     */
    public enum RoleName {
        ROLE_ADMIN,
        ROLE_TEACHER,
        ROLE_PARENT,
    }

}
