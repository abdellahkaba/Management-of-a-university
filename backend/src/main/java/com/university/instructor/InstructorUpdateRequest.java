package com.university.instructor;

import java.time.LocalDate;
public record InstructorUpdateRequest(
        Integer id,
        String name,
        String contact,
        LocalDate hireDate
) {
}
