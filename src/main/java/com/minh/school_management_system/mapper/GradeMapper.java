package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.GradeRequest;
import com.minh.school_management_system.dto.response.GradeResponse;
import com.minh.school_management_system.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "subject", ignore = true)
    public Grade toEntity(GradeRequest request);

    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "subjectName", source = "subject.name")
    public GradeResponse toResponse(Grade entity);

    public void updateEntity(GradeRequest request, @MappingTarget Grade entity);

}
