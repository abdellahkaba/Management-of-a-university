package com.university.courseAssignment;

public record UpdateCourseAssignmentRequest(
        Integer id,
        Integer instructorId,
        Integer courseId
) {
}
