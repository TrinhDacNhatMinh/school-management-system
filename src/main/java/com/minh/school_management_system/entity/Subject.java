package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Subject entity represents a school subject.
 * Each subject has a name and is assigned to a teacher.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Name of the subject
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private SubjectName name;

    /**
     * Teacher responsible for this subject
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    /**
     * List of grades for this subject
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Grade> grades;

    /**
     * Enum for predefined subjects (optional)
     */
    public enum SubjectName {
        MATHEMATICS,
        PHYSICS,
        CHEMISTRY,
        BIOLOGY,
        LITERATURE,
        HISTORY,
        GEOGRAPHY,
        ENGLISH
    }
}
