package com.minh.school_management_system.dto.request;

import com.minh.school_management_system.entity.Subject;
import lombok.Data;

@Data
public class SubjectRequest {
    private Subject.SubjectName name;
    private Long teacherId;
}
