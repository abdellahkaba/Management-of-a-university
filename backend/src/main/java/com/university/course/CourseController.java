package com.university.course;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("course")
@Tag(name = "Course")
public class CourseController {
    private final CourseService service;
    @PostMapping
    public ResponseEntity<String> addCourse(
            @RequestBody @Valid CourseRequest request
    ){
        return ResponseEntity.ok(service.addCourse(request));
    }
}
