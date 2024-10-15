package com.university.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public record DepartmentRequest(
        Integer id,
        @NotEmpty(message = "Le nom de departement est requis")
        @NotBlank(message = "Le nom de departement est requis")
        String name,
        String description,
        @NotNull(message = "Donner l'administrateur de ce departement")
        Integer instructorId
) {
}
