package com.university.student;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Integer id;
    private String name;
    private LocalDate dateNaiss;
}
