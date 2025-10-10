package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.TeacherRequest;
import com.minh.school_management_system.dto.response.TeacherResponse;
import com.minh.school_management_system.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomClasses", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Teacher toEntity(TeacherRequest request);

    @Mapping(target = "schoolName", source = "school.name")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "totalSubjects", expression = "java(entity.getSubjects() != null ? entity.getSubjects().size() : 0)")
    @Mapping(target = "totalHomeroomClasses", expression = "java(entity.getHomeroomClasses() != null ? entity.getHomeroomClasses().size() : 0)")
    public TeacherResponse toResponse(Teacher entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomClasses", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "user", ignore = true)
    public void updateEntity(TeacherRequest request, @MappingTarget Teacher entity);

}
