package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class TeacherRequest {
    private String username;
    private String name;
    private String phone;
    private Long schoolId;
}
