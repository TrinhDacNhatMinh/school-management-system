package com.minh.school_management_system.controller;

import com.minh.school_management_system.dto.request.ClassRoomRequest;
import com.minh.school_management_system.dto.response.ClassRoomResponse;
import com.minh.school_management_system.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PostMapping
    public ResponseEntity<ClassRoomResponse> createClassRoom(@RequestBody ClassRoomRequest request) {
        ClassRoomResponse response = classRoomService.createClassRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClassRoomResponse>> getAllClassRooms() {
        List<ClassRoomResponse> responses = classRoomService.getAllClassRooms();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> getClassRoomById(@PathVariable Long id) {
        ClassRoomResponse response = classRoomService.getClassRoomById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> updateClassRoom(
            @PathVariable Long id,
            @RequestBody ClassRoomRequest request) {
        ClassRoomResponse response = classRoomService.updateClassRoom(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassRoom(@PathVariable Long id) {
        classRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }

}
