package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.StudentRequest;
import com.minh.school_management_system.dto.response.StudentResponse;
import com.minh.school_management_system.entity.Role;
import com.minh.school_management_system.entity.Student;
import com.minh.school_management_system.entity.User;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.StudentMapper;
import com.minh.school_management_system.repository.ClassRoomRepository;
import com.minh.school_management_system.repository.RoleRepository;
import com.minh.school_management_system.repository.StudentRepository;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassRoomRepository classRoomRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);

        student.setClassRoom(classRoomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException("ClassRoom not found with id " + request.getClassroomId())));

        String key = request.getStudentKey();
        String studentCode = generateStudentCode(key);
        student.setStudentCode(studentCode);

        User parentUser = new User();
        parentUser.setUsername(student.getStudentCode());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String defaultPassword = student.getDateOfBirth().format(formatter);
        parentUser.setPassword(passwordEncoder.encode(defaultPassword));
        parentUser.setActive(true);
        parentUser.setRole(roleRepository.findByName(Role.RoleName.ROLE_PARENT)
                .orElseThrow(() -> new ResourceNotFoundException("Parent role not found")));

        userRepository.save(parentUser);
        student.setUser(parentUser);

        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        studentMapper.updateEntity(request, existing);

        existing.setClassRoom(classRoomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException("ClassRoom not found with id " + id)));

        Student updated = studentRepository.save(existing);
        return studentMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }

    /**
     * Generates a unique student code based on a given key.
     * The code format is: {key}{sequence}, e.g., "S2025A001".
     *
     * @param key The prefix for the student code, usually provided by admin (e.g., year + class).
     * @return A unique student code combining the key and a sequential number.
     */
    private String generateStudentCode(String key) {
        // Find the maximum sequence number currently used for this key in the database
        Integer maxSequence = studentRepository.findMaxSequenceByKey(key);

        // Determine the next sequence number: if no previous student exists, start from 1
        int nextSequence = (maxSequence == null ? 1 : maxSequence + 1);

        // Format the sequence number as a 3-digit string with leading zeros (e.g., 001, 002, ...)
        String sequenceStr = String.format("%03d", nextSequence);

        // Combine the key and sequence number to form the final student code
        return key + sequenceStr;
    }

}
