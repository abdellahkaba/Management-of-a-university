package com.university.course;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private Integer id;
    private String title;
    private int credit;
    private String departmentName;
}
