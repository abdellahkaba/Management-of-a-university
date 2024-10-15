package com.university.courseAssignment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("assignment")
public class CourseAssignmentController {
    private final CourseAssignmentService service ;
    @PostMapping
    public ResponseEntity<Integer> assignCourseInstructor(
            @RequestBody @Valid CourseAssignmentRequest request
    ){
        return ResponseEntity.ok(service.assignCourseInstructor(request));
    }
}
