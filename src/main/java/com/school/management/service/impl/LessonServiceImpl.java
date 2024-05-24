package com.school.management.service.impl;

import com.school.management.entity.Lesson;
import com.school.management.exception.NotFoundException;
import com.school.management.mapper.LessonMapper;
import com.school.management.model.LessonDto;
import com.school.management.repository.LessonRepository;
import com.school.management.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final static Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;

    @Override
    public LessonDto saveLesson(LessonDto lessonDto) {
        logger.info("ActionLog.saveLesson.start request: {}", lessonDto);

        var lesson = LessonMapper.INSTANCE.dtoTOEntity(lessonDto);
        var savedLesson = lessonRepository.save(lesson);

        var savedlessonDto = LessonMapper.INSTANCE.entityTODto(savedLesson);
        logger.info("ActionLog.saveLesson.end response: {}", savedlessonDto);
        return savedlessonDto;
    }

    @Override
    public List<LessonDto> getAllLessons() {
        logger.info("ActionLog.getLessons.start");

        var lessons = lessonRepository.findAll();
        var lessonDtoList = LessonMapper.INSTANCE.entityListToDtoList(lessons);

        logger.info("ActionLog.getAllLessons.end lesson count: {}" , lessonDtoList.size());
        return lessonDtoList;
    }

    @Override
    public LessonDto getLesson(int id) {
        logger.info("ActionLog.getLesson.start id: {}", id);

        var lesson =lessonRepository.findById(id)
                .orElseThrow
        (() ->
                        new NotFoundException("Lesson is not found for id: " + id));

        var lessonDto = LessonMapper.INSTANCE.entityTODto(lesson);
        logger.info("ActionLog.getLesson.end id: {}", id);
        return lessonDto;

    }

}
