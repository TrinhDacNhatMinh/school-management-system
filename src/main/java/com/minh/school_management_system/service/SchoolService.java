package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.SchoolRequest;
import com.minh.school_management_system.dto.response.SchoolResponse;

import java.util.List;

public interface SchoolService {

    // CRUD
    public SchoolResponse createSchool(SchoolRequest request);

    public List<SchoolResponse> getAllSchools();

    public SchoolResponse getSchoolById(Long id);

    public SchoolResponse updateSchool(Long id, SchoolRequest request);

    public void deleteSchool(Long id);

}
