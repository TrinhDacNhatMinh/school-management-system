package com.minh.school_management_system.mapper;

import com.minh.school_management_system.dto.request.ClassRoomRequest;
import com.minh.school_management_system.dto.response.ClassRoomResponse;
import com.minh.school_management_system.entity.ClassRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassRoomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomTeacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    public ClassRoom toEntity(ClassRoomRequest request);

    @Mapping(target = "schoolName", source = "school.name")
    @Mapping(target = "homeroomTeacherName", source = "homeroomTeacher.name")
    public ClassRoomResponse toResponse(ClassRoom entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomTeacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    public void updateEntity(ClassRoomRequest request, @MappingTarget ClassRoom entity);

}
