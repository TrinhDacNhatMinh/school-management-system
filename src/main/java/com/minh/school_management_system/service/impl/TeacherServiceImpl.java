package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.TeacherRequest;
import com.minh.school_management_system.dto.request.UpdatedTeacherRequest;
import com.minh.school_management_system.dto.response.TeacherResponse;
import com.minh.school_management_system.entity.Role;
import com.minh.school_management_system.entity.Teacher;
import com.minh.school_management_system.entity.User;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.TeacherMapper;
import com.minh.school_management_system.repository.RoleRepository;
import com.minh.school_management_system.repository.SchoolRepository;
import com.minh.school_management_system.repository.TeacherRepository;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TeacherResponse createTeacher(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);

        teacher.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        User user = new User();
        user.setUsername(request.getUsername());
        String defaultPassword = teacher.getPhone();
        user.setPassword(passwordEncoder.encode(defaultPassword));
        user.setActive(true);
        user.setRole(roleRepository.findByName(Role.RoleName.ROLE_TEACHER)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher role not found")));

        userRepository.save(user);
        teacher.setUser(user);

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
    public void updateTeacher(Long teacherId, UpdatedTeacherRequest request) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + teacherId));

        if (request.getName() != null && !request.getName().isEmpty()) {
            teacher.setName(request.getName());
        }
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            teacher.setPhone(request.getPhone());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            teacher.setAddress(request.getAddress());
        }
        if (request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            teacher.setAvatar(request.getAvatar());
        }

        teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + id));
        userRepository.delete(teacher.getUser());
        teacherRepository.delete(teacher);
    }

}
