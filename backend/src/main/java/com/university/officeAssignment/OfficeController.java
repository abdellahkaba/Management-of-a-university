package com.university.officeAssignment;

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
@RequestMapping("office")
@Tag(name = "OfficeAssignment")
public class OfficeController {
    private final OfficeService service ;
    @PostMapping
    public ResponseEntity<String> createOffice(
            @RequestBody @Valid OfficeRequest request
    ){
        return ResponseEntity.ok(service.createOffice(request));
    }
}
