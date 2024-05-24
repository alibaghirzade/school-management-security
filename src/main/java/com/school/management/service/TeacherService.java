package com.school.management.service;

import com.school.management.model.TeacherDto;
import com.school.management.model.TeacherRequest;
import com.school.management.model.TeacherResponse;

import java.util.List;

public interface TeacherService {
    TeacherResponse saveTeacher (TeacherRequest teacherRequest);

    TeacherDto getTeacher (int id);

    List<TeacherDto> getAllTeachers();

    TeacherResponse updateTeacher (int id, TeacherRequest teacherRequest);

}
