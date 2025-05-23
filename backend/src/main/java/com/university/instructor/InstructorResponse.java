package com.university.instructor;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorResponse {
    private Integer id;
    private String name;
    private String contact;
    private LocalDate hireDate;
}