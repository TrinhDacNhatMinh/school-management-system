package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.GradeRequest;
import com.minh.school_management_system.dto.response.GradeResponse;
import com.minh.school_management_system.entity.Grade;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.GradeMapper;
import com.minh.school_management_system.repository.GradeRepository;
import com.minh.school_management_system.repository.StudentRepository;
import com.minh.school_management_system.repository.SubjectRepository;
import com.minh.school_management_system.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public GradeResponse createGrade(GradeRequest request) {
        Grade grade = gradeMapper.toEntity(request);

        grade.setStudent(studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + request.getStudentId())));

        grade.setSubject(subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + request.getStudentId())));

        Grade saved = gradeRepository.save(grade);
        return gradeMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public GradeResponse getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id " + id));
        return gradeMapper.toResponse(grade);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponse> getGradesByStudent(Long studentId) {
        List<Grade> grades = gradeRepository.findByStudent_Id(studentId);
        return grades.stream()
                .map(gradeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponse> getGradesBySubject(Long subjectId) {
        List<Grade> grades = gradeRepository.findBySubject_Id(subjectId);
        return grades.stream()
                .map(gradeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponse> getGradesBySemester(String semester) {
        List<Grade> grades = gradeRepository.findBySemester(Grade.SemesterName.valueOf(semester));
        return grades.stream()
                .map(gradeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public GradeResponse updateGrade(Long id, GradeRequest request) {
        Grade existing = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with id " + id));

        gradeMapper.updateEntity(request, existing);

        existing.setStudent(studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + request.getStudentId())));

        existing.setSubject(subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + request.getSubjectId())));

        Grade updated = gradeRepository.save(existing);
        return gradeMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Grade not found with id " + id);
        }
        gradeRepository.deleteById(id);
    }

}
