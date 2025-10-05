package com.minh.school_management_system.repository;

import com.minh.school_management_system.entity.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {
}
