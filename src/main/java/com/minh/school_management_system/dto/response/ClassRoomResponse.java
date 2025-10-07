package com.minh.school_management_system.dto.response;

import lombok.Data;

@Data
public class ClassRoomResponse {
    private Long id;
    private String name;
    private String gradeLevel;
    private String schoolYear;
    private String schoolName;
    private String homeroomTeacherName;
}
