package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.SubjectRequest;
import com.minh.school_management_system.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {

    // CRUD
    public SubjectResponse createSubject(SubjectRequest request);

    public List<SubjectResponse> getAllSubjects();

    public SubjectResponse getSubjectById(Long id);

    public SubjectResponse updateSubject(Long id, SubjectRequest request);

    public void deleteSubject(Long id);

    // Manage teacher assignment
    public SubjectResponse assignTeacher(Long subjectId, Long teacherId);

    public SubjectResponse changeTeacher(Long subjectId, Long newTeacherId);

}
