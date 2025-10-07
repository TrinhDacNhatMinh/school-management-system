package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.StudentRequest;
import com.minh.school_management_system.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    // CRUD
    public StudentResponse createStudent(StudentRequest request);

    public List<StudentResponse> getAllStudents();

    public StudentResponse getStudentById(Long id);

    public StudentResponse updateStudent(Long id, StudentRequest request);

    public void deleteStudent(Long id);

}
