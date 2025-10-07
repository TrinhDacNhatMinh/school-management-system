package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.GradeRequest;
import com.minh.school_management_system.dto.response.GradeResponse;

import java.util.List;

public interface GradeService {

    // CRUD
    public GradeResponse createGrade(GradeRequest request);

    public GradeResponse getGradeById(Long id);

    public List<GradeResponse> getGradesByStudent(Long studentId);

    public List<GradeResponse> getGradesBySubject(Long subjectId);

    public List<GradeResponse> getGradesBySemester(String semester);

    public GradeResponse updateGrade(Long id, GradeRequest request);

    public void deleteGrade(Long id);

}
