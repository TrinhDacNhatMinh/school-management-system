package com.minh.school_management_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GradeResponse {
    private Long id;
    private String semester;
    private Double score;
    private String schoolYear;
    private LocalDate createAt;
    private String studentName;
    private String subjectName;
}
