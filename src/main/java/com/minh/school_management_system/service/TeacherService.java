package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.TeacherRequest;
import com.minh.school_management_system.dto.request.UpdatedTeacherRequest;
import com.minh.school_management_system.dto.response.TeacherResponse;

import java.util.List;

public interface TeacherService {

    public TeacherResponse createTeacher(TeacherRequest request);

    public List<TeacherResponse> getAllTeachers();

    public TeacherResponse getTeacherById(Long id);

    public TeacherResponse updateTeacher(Long teacherId, UpdatedTeacherRequest request);

    public void deleteTeacher(Long id);

}
