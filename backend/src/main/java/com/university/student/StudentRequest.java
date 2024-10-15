package com.university.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
public record StudentRequest(
        Integer id,
        @NotEmpty(message = "Le nom complet est requis")
        @NotBlank(message = "Le nom complet est requis")
        String name,
        @NotNull(message = "La date de naissance est requise")
        @Past(message = "La date de naissance doit être dans le passé")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "fr")
        LocalDate dateNaiss
) {
}
