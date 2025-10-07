package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.SubjectRequest;
import com.minh.school_management_system.dto.response.SubjectResponse;
import com.minh.school_management_system.entity.Subject;
import com.minh.school_management_system.entity.Teacher;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.SubjectMapper;
import com.minh.school_management_system.repository.SubjectRepository;
import com.minh.school_management_system.repository.TeacherRepository;
import com.minh.school_management_system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public SubjectResponse createSubject(SubjectRequest request) {
        Subject subject = subjectMapper.toEntity(request);

        subject.setTeacher(teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + request.getTeacherId())));

        Subject saved = subjectRepository.save(subject);
        return subjectMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + id));
        return subjectMapper.toResponse(subject);
    }

    @Override
    @Transactional
    public SubjectResponse updateSubject(Long id, SubjectRequest request) {
        Subject existing = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + id));

        subjectMapper.updateEntity(request, existing);

        existing.setTeacher(teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + request.getTeacherId())));

        Subject updated = subjectRepository.save(existing);
        return subjectMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id " + id);
        }
        subjectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SubjectResponse assignTeacher(Long subjectId, Long teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
        subject.setTeacher(teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + teacherId)));

        Subject saved = subjectRepository.save(subject);
        return subjectMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SubjectResponse changeTeacher(Long subjectId, Long newTeacherId) {
        return assignTeacher(subjectId, newTeacherId);
    }

}
