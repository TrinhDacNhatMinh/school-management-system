package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.TeacherRequest;
import com.minh.school_management_system.dto.response.TeacherResponse;

import java.util.List;

public interface TeacherService {

    // CRUD
    public TeacherResponse createTeacher(TeacherRequest request);

    public List<TeacherResponse> getAllTeachers();

    public TeacherResponse getTeacherById(Long id);

    public TeacherResponse updateTeacher(Long id, TeacherRequest request);

    public void deleteTeacher(Long id);

}
