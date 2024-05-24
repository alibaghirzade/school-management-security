package com.school.management.controller;

import com.school.management.model.TeacherDto;
import com.school.management.model.TeacherRequest;
import com.school.management.model.TeacherResponse;
import com.school.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public TeacherResponse saveTeacher(@RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(teacherRequest);
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable int id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping()
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/{id}")
    public TeacherResponse updateTeacher(@PathVariable int id, @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }
}
