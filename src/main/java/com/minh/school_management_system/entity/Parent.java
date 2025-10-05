package com.minh.school_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parents")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Full name of the parent
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Contact phone number
     */
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    /**
     * Address of the parent
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Associated student
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    /**
     * Associated user account
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

}
