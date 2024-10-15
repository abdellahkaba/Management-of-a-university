package com.university.enrollment;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponse {
    private Integer id;
    private String studentName;
    private String courseTitle;
    private String grade;
    private String departmentName;
}
