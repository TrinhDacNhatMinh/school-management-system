package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * School entity represents a high school in the system.
 * Each school has a name, address, phone number, and email.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name of the school
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Address of the school
     */
    @Column(name = "address",nullable = false, length = 50)
    private String address;

    /**
     * Contact phone number
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Contact email
     */
    @Column(name = "email", length = 50)
    private String email;

    /**
     * List of classes that belong to this school.
     * Deleting the school will also remove all its classes.
     */
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassRoom> classes;

}
