package com.minh.school_management_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {
    private Long id;
    private String studentCode;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String classroomName;
    private String parentUsername;
}
