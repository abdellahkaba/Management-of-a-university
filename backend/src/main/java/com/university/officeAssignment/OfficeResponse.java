package com.university.officeAssignment;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeResponse {
    private Integer id;
    private String location;
    private String InstructorName;
}