package com.university.instructor;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<InstructorResponse>> listAllInstructor(){
        return ResponseEntity.ok(service.listAllInstructor());
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<InstructorResponse> getInstructorById(
            @PathVariable("instructor-id") Integer instructorId
    ){
        return ResponseEntity.ok(service.getInstructorById(instructorId));
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<Void> updateInstructor(
            @PathVariable("instructor-id") Integer id,
            @RequestBody InstructorUpdateRequest request
    ){
        request = new InstructorUpdateRequest(
                id, request.name(), request.contact(), request.hireDate()
        );
        service.updateInstructor(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<Void> deleteInstructor(
            @PathVariable("instructor-id") Integer instructorId
    ){
        service.deleteInstructor(instructorId);
        return ResponseEntity.accepted().build();
    }
}
