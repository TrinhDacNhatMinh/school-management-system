package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Student code
     */
    @Column(name = "student_code", nullable = false, unique = true, length = 20)
    private String studentCode;

    /**
     * Full name of the student
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Date of birth
     */
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Gender of the student
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderName gender;

    /**
     * Address of the student
     */
    @Column(name = "address", length = 50)
    private String address;

    /**
     * Classroom the student belongs to
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    /**
     * List of grades for the student
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades;

    /**
     * One-to-one relationship to the User account
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Gender enum
     */
    public enum GenderName {
        MALE,
        FEMALE
    }

}
