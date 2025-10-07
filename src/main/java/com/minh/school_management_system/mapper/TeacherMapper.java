package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.StaffRequest;
import com.minh.school_management_system.dto.response.StaffResponse;
import com.minh.school_management_system.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomClasses", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Teacher toEntity(StaffRequest request);

    @Mapping(target = "schoolName", source = "school.name")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "totalSubjects", expression = "java(entity.getSubjects() != null ? entity.getSubjects().size() : 0)")
    @Mapping(target = "totalHomeroomClasses", expression = "java(entity.getHomeroomClasses() != null ? entity.getHomeroomClasses().size() : 0)")
    public StaffResponse toResponse(Teacher entity);

    public void updateEntity(StaffRequest request, @MappingTarget Teacher entity);

}
