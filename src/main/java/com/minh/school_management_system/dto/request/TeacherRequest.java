package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class StaffRequest {
    private String name;
    private String phone;
    private Long schoolId;
    private Long userId;
}
