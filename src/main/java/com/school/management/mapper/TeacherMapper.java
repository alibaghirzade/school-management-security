package com.school.management.mapper;

import com.school.management.entity.Lesson;
import com.school.management.entity.Teacher;
import com.school.management.model.LessonDto;
import com.school.management.model.TeacherDto;
import com.school.management.model.TeacherRequest;
import com.school.management.model.TeacherResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TeacherMapper {
    public static final TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "teacherRequest.name")
    @Mapping(target = "lesson", source = "lesson")
    public abstract Teacher modelToEntity(TeacherRequest teacherRequest, Lesson lesson);


    @Mapping(target = "id", source = "teacher.id")
    @Mapping(target = "name", source = "teacher.name")
    @Mapping(target = "lesson", source = "lesson.name")
    public abstract TeacherResponse entityToModel(Teacher teacher, Lesson lesson);

    public abstract Teacher dtoToEntity(TeacherDto teacherDto);

    public abstract TeacherDto entityToDto(Teacher teacher);

    public abstract List<TeacherDto> entityListToDtoList(List<Teacher> teachers);


    @Mapping(target = "lesson.id", source = "lessonId")
    public abstract Teacher modelToEntity(@MappingTarget Teacher entity, TeacherRequest teacherRequest);
}
