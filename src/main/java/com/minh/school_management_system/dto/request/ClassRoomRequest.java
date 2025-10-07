package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class ClassRoomRequest {
    private String name;
    private String gradeLevel;
    private String schoolYear;
    private Long schoolId;
    private Long homeroomTeacherId;
}
