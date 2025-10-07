package com.minh.school_management_system.service.impl;

import com.minh.school_management_system.dto.request.ClassRoomRequest;
import com.minh.school_management_system.dto.response.ClassRoomResponse;
import com.minh.school_management_system.entity.ClassRoom;
import com.minh.school_management_system.exception.ResourceNotFoundException;
import com.minh.school_management_system.mapper.ClassRoomMapper;
import com.minh.school_management_system.repository.ClassRoomRepository;
import com.minh.school_management_system.repository.SchoolRepository;
import com.minh.school_management_system.repository.TeacherRepository;
import com.minh.school_management_system.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomMapper classRoomMapper;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public ClassRoomResponse createClassRoom(ClassRoomRequest request) {
        ClassRoom classRoom = classRoomMapper.toEntity(request);

        classRoom.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        classRoom.setHomeroomTeacher(teacherRepository.findById(request.getHomeroomTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + request.getHomeroomTeacherId())));

        ClassRoom saved = classRoomRepository.save(classRoom);
        return classRoomMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassRoomResponse> getAllClassRooms() {
        return classRoomRepository.findAll()
                .stream()
                .map(classRoomMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClassRoomResponse getClassRoomById(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassRoom not found with id: " + id));
        return classRoomMapper.toResponse(classRoom);
    }

    @Override
    @Transactional
    public ClassRoomResponse updateClassRoom(Long id, ClassRoomRequest request) {
        ClassRoom existing = classRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassRoom not found with id: " + id));

        classRoomMapper.updateEntity(request, existing);

        existing.setSchool(schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id " + request.getSchoolId())));

        existing.setHomeroomTeacher(teacherRepository.findById(request.getHomeroomTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id " + request.getHomeroomTeacherId())));

        ClassRoom updated = classRoomRepository.save(existing);
        return classRoomMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteClassRoom(Long id) {
        if (!classRoomRepository.existsById(id)) {
            throw new ResourceNotFoundException("ClassRoom not found with id " + id);
        }
        classRoomRepository.deleteById(id);
    }

}
