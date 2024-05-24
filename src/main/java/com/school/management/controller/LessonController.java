package com.school.management.controller;

import com.school.management.model.LessonDto;
import com.school.management.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    public LessonDto save(@RequestBody LessonDto lessonDto) {
        return lessonService.saveLesson(lessonDto);
    }

    @GetMapping
    public List<LessonDto> getAllLessons (){
        return lessonService.getAllLessons();
    }

    @GetMapping("{id}")
    public LessonDto getLesson (@PathVariable int id){
        return lessonService.getLesson(id);
    }
}
