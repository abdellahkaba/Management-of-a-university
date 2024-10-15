package com.university.course;

import jakarta.validation.constraints.*;
public record CourseRequest(
        Integer id,
        @NotEmpty(message = "donner le titre du cours")
        @NotBlank(message = "donner le titre du cours")
        String title,
        @NotNull(message = "attribuer un credit a ce cours")
        @Min(0)
        @Max(5)
        int credit,
        @NotNull(message = "attribuer ce cours a un departement")
        Integer departmentId
) {
}
