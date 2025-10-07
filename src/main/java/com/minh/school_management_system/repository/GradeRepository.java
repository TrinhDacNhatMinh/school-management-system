package com.minh.school_management_system.repository;

import com.minh.school_management_system.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    public List<Grade> findByStudent_Id(Long studentId);

    public List<Grade> findBySubject_Id(Long subjectId);

    public List<Grade> findBySemester(Grade.SemesterName semester);

}
