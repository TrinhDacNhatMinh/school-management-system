package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.SchoolRequest;
import com.minh.school_management_system.dto.response.SchoolResponse;
import com.minh.school_management_system.entity.School;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.SchoolMapper;
import com.minh.school_management_system.repository.SchoolRepository;
import com.minh.school_management_system.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    @Transactional
    public SchoolResponse createSchool(SchoolRequest request) {
        School school = schoolMapper.toEntity(request);
        School saved = schoolRepository.save(school);
        return schoolMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchoolResponse> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolResponse getSchoolById(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + id));
        return schoolMapper.toResponse(school);
    }

    @Override
    @Transactional
    public SchoolResponse updateSchool(Long id, SchoolRequest request) {
        School existing = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + id));

        schoolMapper.updateEntity(request, existing);

        School updated = schoolRepository.save(existing);
        return schoolMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteSchool(Long id) {
        if (!schoolRepository.existsById(id)) {
            throw new ResourceNotFoundException("School not found with id " + id);
        }
        schoolRepository.deleteById(id);
    }

}
