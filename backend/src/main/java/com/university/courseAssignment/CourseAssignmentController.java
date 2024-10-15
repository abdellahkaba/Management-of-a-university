package com.university.courseAssignment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CourseAssignmentResponse>> listCourseInstructors(){
        return ResponseEntity.ok(service.listCourseInstructors());
    }

    @GetMapping("/{assignment-id}")
    public ResponseEntity<CourseAssignmentResponse> getCourseAssign(
            @PathVariable("assignment-id") Integer assignmentId
    ){
        return ResponseEntity.ok(service.getCourseAssign(assignmentId));
    }

    @DeleteMapping("/{assignment-id}")
    public ResponseEntity<Void> deleteCourseAssign(
            @PathVariable("assignment-id") Integer assignmentId
    ){
        service.deleteCourseAssign(assignmentId);
        return ResponseEntity.accepted().build();
    }
}
