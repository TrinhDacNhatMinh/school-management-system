package com.minh.school_management_system.dto.request;

import com.minh.school_management_system.entity.Student;
import lombok.Data;

import java.util.Date;

@Data
public class StudentRequest {
    private String name;
    private Date dateOfBirth;
    private Student.GenderName gender;
    private String address;
    private Long classroomId;
    private String studentKey;
}
