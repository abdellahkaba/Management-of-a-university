package com.university.enrollment;

public record UpdateEnrollmentRequest(
        Integer id,
        Grade grade,
        Integer studentId,
        Integer courseId
) {
}
