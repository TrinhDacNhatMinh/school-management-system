package com.minh.school_management_system.repository;

import com.minh.school_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT MAX(SUBSTRING(s.studentCode, LENGTH(:key) + 1, 3)) FROM Student s WHERE s.studentCode LIKE CONCAT(:key, '%')")
    public Integer findMaxSequenceByKey(@Param("key") String key);

    public Optional<Student> findByUser_Username(String username);

}
