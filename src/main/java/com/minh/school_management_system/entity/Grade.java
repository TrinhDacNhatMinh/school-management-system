package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Grade entity represents the score of a student in a particular subject and semester.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Semester name or term (e.g., "Fall 2025")
     */
    @Column(name = "semester", nullable = false, length = 20)
    private String semester;

    /**
     * Score obtained by the student for the subject
     */
    @Column(name = "score", nullable = false)
    @Min(0)
    @Max(10)
    private Double score;

    /**
     * Date when the grade record was created.
     * This field is automatically set to the current date when a new Grade is created.
     */
    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    /**
     * Student who received the grade
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * Subject associated with the grade
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}
