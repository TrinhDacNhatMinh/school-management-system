package com.minh.school_management_system.controller;

import com.minh.school_management_system.dto.request.SubjectRequest;
import com.minh.school_management_system.dto.response.SubjectResponse;
import com.minh.school_management_system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponse> createSubject(@RequestBody SubjectRequest request) {
        SubjectResponse response = subjectService.createSubject(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        List<SubjectResponse> responses = subjectService.getAllSubjects();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Long id) {
        SubjectResponse response = subjectService.getSubjectById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(
            @PathVariable Long id,
            @RequestBody SubjectRequest request) {
        SubjectResponse response = subjectService.updateSubject(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{subjectId}/assign-teacher/{teacherId}")
    public ResponseEntity<SubjectResponse> assignTeacher(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId) {
        SubjectResponse response = subjectService.assignTeacher(subjectId, teacherId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{subjectId}/change-teacher/{newTeacherId}")
    public ResponseEntity<SubjectResponse> changeTeacher(
            @PathVariable Long subjectId,
            @PathVariable Long newTeacherId) {
        SubjectResponse response = subjectService.changeTeacher(subjectId, newTeacherId);
        return ResponseEntity.ok(response);
    }

}
