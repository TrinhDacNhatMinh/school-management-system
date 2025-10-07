package com.minh.school_management_system.dto.request;

import com.minh.school_management_system.entity.Grade;
import lombok.Data;

@Data
public class GradeRequest {
    private Grade.SemesterName semester;
    private Double score;
    private String schoolYear;
    private Long studentId;
    private Long subjectId;
}
