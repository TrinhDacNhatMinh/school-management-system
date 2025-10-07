package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.StaffRequest;
import com.minh.school_management_system.dto.response.StaffResponse;

import java.util.List;

public interface StaffService {

    // CRUD
    public StaffResponse createStaff(StaffRequest request);

    public List<StaffResponse> getAllStaffs();

    public StaffResponse getStaffById(Long id);

    public StaffResponse updateStaff(Long id, StaffRequest request);

    public void deleteStaff(Long id);

    // Manage staff roles
    public StaffResponse assignRole(Long staffId, Long roleId);

    public StaffResponse changeRole(Long staffId, Long newRoleId);

    public void removeRole(Long staffId);

}
