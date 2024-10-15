package com.university.course;

public record UpdateCourseRequest(
        Integer id,
        String title,
        int credit,
        Integer departmentId
) {
}
