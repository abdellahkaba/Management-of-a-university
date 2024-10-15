package com.university.courseAssignment;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseAssignmentResponse {
    private Integer id;
    private String instructorName;
    private String courseTitle;
    private int creditCourse;
    private String departmentName;
}
