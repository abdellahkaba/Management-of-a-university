package com.university.instructor;

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
@RequestMapping("instructor")
@Tag(name = "Instructor")
public class InstructorController {
    private final InstructorService service;
    @PostMapping
    public ResponseEntity<Integer> addInstructor(
            @RequestBody @Valid InstructorRequest request
    ){
        return ResponseEntity.ok(service.addInstructor(request));
    }
}
