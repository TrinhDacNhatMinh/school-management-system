package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.SchoolRequest;
import com.minh.school_management_system.dto.response.SchoolResponse;
import com.minh.school_management_system.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SchoolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classes", ignore = true)
    public School toEntity(SchoolRequest request);

    @Mapping(target = "totalClasses", expression = "java(entity.getClasses() != null ? entity.getClasses().size() : 0)")
    public SchoolResponse toResponse(School entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "classes", ignore = true)
    public void updateEntity(SchoolRequest request, @MappingTarget School entity);

}
