package com.university.courseAssignment;

import jakarta.validation.constraints.NotNull;
public record CourseAssignmentRequest(
        Integer id,
        @NotNull(message = "Choisir un professeur")
        Integer instructorId,
        @NotNull(message = "Choisir un cours")
        Integer courseId
) {
}
