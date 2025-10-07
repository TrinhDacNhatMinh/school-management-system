package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.TeacherRequest;
import com.minh.school_management_system.dto.response.TeacherResponse;
import com.minh.school_management_system.entity.Teacher;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.TeacherMapper;
import com.minh.school_management_system.repository.SchoolRepository;
import com.minh.school_management_system.repository.TeacherRepository;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TeacherResponse createTeacher(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);

        teacher.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        teacher.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId())));

        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + id));
        return teacherMapper.toResponse(teacher);
    }

    @Override
    @Transactional
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + id));

        teacherMapper.updateEntity(request, existing);

        existing.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        existing.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId())));

        Teacher updated = teacherRepository.save(existing);
        return teacherMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with id " + id);
        }
        teacherRepository.deleteById(id);
    }

}
