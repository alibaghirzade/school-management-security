package com.school.management.service;

import com.school.management.entity.Lesson;
import com.school.management.model.LessonDto;

import java.util.List;

public interface LessonService {
    LessonDto saveLesson(LessonDto lessonDto);
    List<LessonDto> getAllLessons();
    LessonDto getLesson(int id);

}
