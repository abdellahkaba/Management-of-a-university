package com.university.instructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
public record InstructorRequest(
        Integer id,
        @NotEmpty(message = "Le nom est requis")
        @NotBlank(message = "Le nom est requis")
        String name,
        @NotEmpty(message = "Le numéro de téléphone est requis")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit être valide (ex : +1234567890)")
        String contact,
        LocalDate hireDate
) {
}
