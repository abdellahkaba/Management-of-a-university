package com.university.officeAssignment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OfficeResponse>> listAllOffices(){
        return ResponseEntity.ok(service.listAllOffices());
    }
    @GetMapping("/{office-id}")
    public ResponseEntity<OfficeResponse> officeDetail(
            @PathVariable("office-id") Integer officeId
    ){
        return ResponseEntity.ok(service.officeDetail(officeId));
    }
}
