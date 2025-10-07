package com.minh.school_management_system.dto.response;

import lombok.Data;

@Data
public class StaffResponse {
    private Long id;
    private String name;
    private String phone;
    private String schoolName;
    private String username;
    private int totalSubjects;
    private int totalHomeroomClasses;
}
