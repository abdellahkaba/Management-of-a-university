package com.university.course;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CourseResponse>> listCourses(){
        return ResponseEntity.ok(service.listCourse());
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable("course-id") Integer courseId
    ){
        return ResponseEntity.ok(service.getCourseById(courseId));
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<Void> updateCourse(
            @PathVariable("course-id") Integer id,
            @RequestBody UpdateCourseRequest request
    ){
        request = new UpdateCourseRequest(
                id, request.title(), request.credit(), request.departmentId()
        );
        service.updateCourse(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable("course-id") Integer courseId
    ){
        service.deleteCourse(courseId);
        return ResponseEntity.accepted().build();
    }
}
