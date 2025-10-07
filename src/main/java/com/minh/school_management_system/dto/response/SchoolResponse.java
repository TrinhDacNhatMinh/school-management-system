package com.minh.school_management_system.dto.response;

import lombok.Data;

@Data
public class SchoolResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int totalClasses;
}
