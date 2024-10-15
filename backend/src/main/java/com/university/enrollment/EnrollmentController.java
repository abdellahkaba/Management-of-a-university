package com.university.enrollment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("enrollment")
@Tag(name = "Enrollment")
public class EnrollmentController {
    private final EnrollmentService service ;
    @PostMapping
    public ResponseEntity<Integer> enrollStudent(
            @RequestBody @Valid EnrollmentRequest request
    ){
        return ResponseEntity.ok(service.enrollStudent(request)) ;
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> listStudentEnroll(){
        return ResponseEntity.ok(service.listStudentEnroll());
    }

    @GetMapping("/{enroll-id}")
    public ResponseEntity<EnrollmentResponse> getEnrollStudentById(
            @PathVariable("enroll-id") Integer enrollId
    ){
        return ResponseEntity.ok(service.getEnrollStudentById(enrollId));
    }

    @PutMapping("/{enroll-id}")
    public ResponseEntity<Void> updateEnrollment(
            @PathVariable("enroll-id") Integer id,
            @RequestBody @Valid UpdateEnrollmentRequest request
    ){
        request = new UpdateEnrollmentRequest(
                id, request.grade(), request.studentId(), request.courseId()
        );
        service.updateEnrollment(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{enroll-id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable("enroll-id") Integer enrollId
    ){
        service.deleteEnrollment(enrollId);
        return ResponseEntity.accepted().build();
    }
}
