package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.StaffRequest;
import com.minh.school_management_system.dto.response.StaffResponse;
import com.minh.school_management_system.entity.Role;
import com.minh.school_management_system.entity.Teacher;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.StaffMapper;
import com.minh.school_management_system.repository.RoleRepository;
import com.minh.school_management_system.repository.SchoolRepository;
import com.minh.school_management_system.repository.StaffRepository;
import com.minh.school_management_system.repository.UserRepository;
import com.minh.school_management_system.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public StaffResponse createStaff(StaffRequest request) {
        Teacher teacher = staffMapper.toEntity(request);

        teacher.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        teacher.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId())));

        Teacher saved = staffRepository.save(teacher);
        return staffMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffResponse> getAllStaffs() {
        return staffRepository.findAll()
                .stream()
                .map(staffMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StaffResponse getStaffById(Long id) {
        Teacher teacher = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
        return staffMapper.toResponse(teacher);
    }

    @Override
    @Transactional
    public StaffResponse updateStaff(Long id, StaffRequest request) {
        Teacher existing = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));

        staffMapper.updateEntity(request, existing);

        existing.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        existing.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + request.getUserId())));

        Teacher updated = staffRepository.save(existing);
        return staffMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id " + id);
        }
        staffRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StaffResponse assignRole(Long staffId, Long roleId) {
        Teacher teacher = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + staffId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));

        teacher.getUser().setRole(role);
        Teacher saved = staffRepository.save(teacher);
        return staffMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StaffResponse changeRole(Long staffId, Long newRoleId) {
        return assignRole(staffId, newRoleId);
    }

    @Override
    @Transactional
    public void removeRole(Long staffId) {
        Teacher teacher = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + staffId));
        Role defaultRole = roleRepository.findByName(Role.RoleName.ROLE_UNASSIGNED)
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found"));
        teacher.getUser().setRole(defaultRole);
        staffRepository.save(teacher);
    }

}
