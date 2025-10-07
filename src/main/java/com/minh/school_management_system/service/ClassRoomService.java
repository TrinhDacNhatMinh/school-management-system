package com.minh.school_management_system.service;

import com.minh.school_management_system.dto.request.ClassRoomRequest;
import com.minh.school_management_system.dto.response.ClassRoomResponse;

import java.util.List;

public interface ClassRoomService {

    // CRUD
    public ClassRoomResponse createClassRoom(ClassRoomRequest request);

    public List<ClassRoomResponse> getAllClassRooms();

    public ClassRoomResponse getClassRoomById(Long id);

    public ClassRoomResponse updateClassRoom(Long id, ClassRoomRequest request);

    public void deleteClassRoom(Long id);

}
