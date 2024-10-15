package com.university.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
public record UpdateStudentRequest(
        Integer id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "fr")
        LocalDate dateNaiss
) {
}
