package com.university.department;

public record UpdateDepartmentRequest(
        Integer id,
        String name,
        String description,
        Integer instructorId
) {
}