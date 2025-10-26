package com.minh.school_management_system.controller;

import com.minh.school_management_system.dto.request.GradeRequest;
import com.minh.school_management_system.dto.response.GradeResponse;
import com.minh.school_management_system.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeResponse> createGrade(@RequestBody GradeRequest request) {
        GradeResponse response = gradeService.createGrade(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeResponse> getGradeById(@PathVariable Long id) {
        GradeResponse response = gradeService.getGradeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponse>> getGradesByStudent(@PathVariable Long studentId) {
        List<GradeResponse> responses = gradeService.getGradesByStudent(studentId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/my-children")
    public ResponseEntity<List<GradeResponse>> getGradesOfMyChildren(){
        List<GradeResponse> responses = gradeService.getGradesOfMyChildren();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<GradeResponse>> getGradesBySubject(@PathVariable Long subjectId) {
        List<GradeResponse> responses = gradeService.getGradesBySubject(subjectId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeResponse> updateGrade(@PathVariable Long id, @RequestBody GradeRequest request) {
        GradeResponse response = gradeService.updateGrade(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }

}
