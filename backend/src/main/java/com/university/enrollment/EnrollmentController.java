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
}
