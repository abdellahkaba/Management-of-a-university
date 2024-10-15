package com.university.department;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("department")
@Tag(name = "Department")
public class DepartmentController {
    private final DepartmentService service ;
    @PostMapping
    public ResponseEntity<String> createDepartment(
            @RequestBody @Valid DepartmentRequest request
    ){
        return ResponseEntity.ok(service.createDepartment(request));
    }
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartment(){
        return ResponseEntity.ok(service.getAllDepartment());
    }
}
