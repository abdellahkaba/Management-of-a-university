package com.university.student;

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
@RequestMapping("student")
@Tag(name = "Student")
public class StudentController {
    private final StudentService service ;
    @PostMapping
    public ResponseEntity<Integer> newStudent (
            @RequestBody @Valid StudentRequest request
    ){
        return ResponseEntity.ok(service.newStudent(request));
    }
}
