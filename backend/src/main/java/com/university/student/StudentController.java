package com.university.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<StudentResponse>> listAllStudent(){
        return ResponseEntity.ok(service.listAllStudent());
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable("student-id") Integer studentId
    ){
        return ResponseEntity.ok(service.getStudentById(studentId)) ;
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable("student-id") Integer id,
            @RequestBody UpdateStudentRequest request
    ){
        request = new UpdateStudentRequest(
                id,
                request.name(), request.dateNaiss()
        );
        service.updateStudent(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("student-id") Integer studentId
    ){
        service.deleteStudent(studentId);
        return ResponseEntity.accepted().build();
    }
}
