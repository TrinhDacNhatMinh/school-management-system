package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * ClassRoom entity represents a class in a school.
 * Each class has a name, grade level, school year, a homeroom teacher, and belongs to a school.
 * It can contain multiple students.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classrooms")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name of the class, e.g., "10A1"
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * Grade level, e.g., "10"
     */
    @Column(name = "grade_level", nullable = false, length = 10)
    private String gradeLevel;

    /**
     * School year, e.g., "2025-2026"
     */
    @Column(name = "school_year", nullable = false, length = 20)
    private String schoolYear;

    /**
     * Homeroom teacher of the class
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "homeroom_teacher_id", nullable = false)
    private Staff homeroomTeacher;

    /**
     * The school this class belongs to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    /**
     * List of students in the class
     */
    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Student> students;

}
