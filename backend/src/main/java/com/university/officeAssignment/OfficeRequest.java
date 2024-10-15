package com.university.officeAssignment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public record OfficeRequest(
        Integer id,
        @NotBlank(message = "La location est requise")
        @NotEmpty(message = "La location est requise")
        String location,
        @NotNull(message = "Instructeur requis")
        Integer instructorId
) {
}
