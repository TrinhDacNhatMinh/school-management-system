package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Staff entity represents a staff member in a school,
 * including teachers, admin staff, etc.
 * Each staff is associated with a User account and a School.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Full name of the staff member
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Contact phone number
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Many-to-one relationship to the School
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    /**
     * List of classrooms where this staff member is the homeroom teacher.
     */
    @OneToMany(mappedBy = "homeroomTeacher", fetch = FetchType.LAZY)
    private List<ClassRoom> homeroomClasses;

    /**
     * List of subjects taught by this staff member (if they are a teacher).
     */
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Subject> subjects;

    /**
     * One-to-one relationship to the User account
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}
