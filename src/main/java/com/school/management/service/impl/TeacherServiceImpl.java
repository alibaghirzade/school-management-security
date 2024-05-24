package com.school.management.service.impl;

import com.school.management.entity.Lesson;
import com.school.management.entity.Teacher;
import com.school.management.exception.NotFoundException;
import com.school.management.mapper.LessonMapper;
import com.school.management.mapper.TeacherMapper;
import com.school.management.model.LessonDto;
import com.school.management.model.TeacherDto;
import com.school.management.model.TeacherRequest;
import com.school.management.model.TeacherResponse;
import com.school.management.repository.TeacherRepository;
import com.school.management.service.LessonService;
import com.school.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;

    private final LessonService lessonService;

    @Override
    public TeacherResponse saveTeacher(TeacherRequest teacherRequest) {
        logger.info("ActionLog.saveTeacher.start request: {}", teacherRequest);

        var lessonDto = lessonService.getLesson(teacherRequest.getLessonId());
        var lesson = LessonMapper.INSTANCE.dtoTOEntity(lessonDto);

        var teacher = TeacherMapper.INSTANCE.modelToEntity(teacherRequest, lesson);
        var savedTeacher = teacherRepository.save(teacher);
        var response = TeacherMapper.INSTANCE.entityToModel(savedTeacher, lesson);

        logger.info("ActionLog.saveTeacher.end response: {}", response);
        return response;
    }

    @Override
    public TeacherDto getTeacher(int id) {
        logger.info("ActionLog.getTeacher.start id: {}", id);

        var teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher is not found for id: " + id));
        var teacherDto = TeacherMapper.INSTANCE.entityToDto(teacher);

        logger.info("ActionLog.getTeacher.end id: {}", id);
        return teacherDto;

    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        logger.info("ActionLog.getTeacher.start");

        var teachers = teacherRepository.findAll();
        var teacherDtoList = TeacherMapper.INSTANCE.entityListToDtoList(teachers);

        logger.info("ActionLog.getAllTeachers.end teachers count: {}", teacherDtoList.size());

        return teacherDtoList;
    }

    @Override
    public TeacherResponse updateTeacher(int id, TeacherRequest teacherRequest) {
        logger.info("ActionLog.updateTeacher.start id: {}" + id);

        var teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Teacher is not found for id: " + id));

        var updatedTeacher = TeacherMapper.INSTANCE.modelToEntity(teacher, teacherRequest);

        var lessonDto = lessonService.getLesson(teacherRequest.getLessonId());
        var lesson = LessonMapper.INSTANCE.dtoTOEntity(lessonDto);

        var response = TeacherMapper.INSTANCE.entityToModel(updatedTeacher, lesson);

        logger.info("ActionLog.updateTeacher.end id: {}" + id);
        return response;
    }
}
