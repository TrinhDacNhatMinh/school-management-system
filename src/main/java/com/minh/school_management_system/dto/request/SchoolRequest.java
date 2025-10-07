package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class SchoolRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
}
