package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.StudentRequest;
import com.minh.school_management_system.dto.response.StudentResponse;
import com.minh.school_management_system.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classRoom", ignore = true)
    @Mapping(target = "grades", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Student toEntity(StudentRequest request);

    @Mapping(target = "classroomName", source = "classroom.name")
    @Mapping(target = "parentUsername",source = "user.username")
    public StudentResponse toResponse(Student entity);

    public void updateEntity(StudentRequest request, @MappingTarget Student entity);

}
