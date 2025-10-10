package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.SubjectRequest;
import com.minh.school_management_system.dto.response.SubjectResponse;
import com.minh.school_management_system.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grades", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    public Subject toEntity(SubjectRequest request);

    @Mapping(target = "teacherName", source = "teacher.name")
    public SubjectResponse toResponse(Subject entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grades", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    public void updateEntity(SubjectRequest request, @MappingTarget Subject entity);

}
