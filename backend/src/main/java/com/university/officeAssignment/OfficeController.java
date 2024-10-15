package com.university.officeAssignment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{office-id}")
    public ResponseEntity<String> updateOffice(
            @PathVariable("office-id") Integer id,
            @RequestBody @Valid UpdateOfficeRequest request
    ){
        request = new UpdateOfficeRequest(
                id, request.location(), request.instructorId()
        );
        service.updateOffice(request);
        return ResponseEntity.accepted().build();
    }
}
