package com.university.officeAssignment;

public record UpdateOfficeRequest(
        Integer id,
        String location,
        Integer instructorId
) {
}
