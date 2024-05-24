package com.school.management.mapper;

import com.school.management.entity.Lesson;
import com.school.management.model.LessonDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public abstract class LessonMapper {
    public static final LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);


    public abstract Lesson dtoTOEntity(LessonDto lessonDto);

    public abstract LessonDto entityTODto(Lesson lesson);

    public abstract List<LessonDto> entityListToDtoList(List<Lesson> lessons);


}
