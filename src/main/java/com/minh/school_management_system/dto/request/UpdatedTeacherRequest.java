package com.minh.school_management_system.dto.request;

import lombok.Data;

@Data
public class UpdatedTeacherRequest {
    private String name;
    private String phone;
    private String address;
    private String avatar;
}
