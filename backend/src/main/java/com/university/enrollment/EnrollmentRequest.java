package com.university.enrollment;

import jakarta.validation.constraints.NotNull;
public record EnrollmentRequest(
        Integer id,
        @NotNull(message = "Donner un grade")
        Grade grade,
        @NotNull(message = "Donner l'étudiant")
        Integer studentId,
        @NotNull(message = "Donner le cours")
        Integer courseId
) {
}
