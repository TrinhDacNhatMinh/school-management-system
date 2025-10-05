package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * StudentCard entity represents a student identification card.
 * Each student can have one or more student cards.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_cards")
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Unique card number
     */
    @Column(name = "card_number", nullable = false, unique = true, length = 20)
    private String cardNumber;

    /**
     * Date when the card was issued
     */
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    /**
     * Student associated with this card
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}
