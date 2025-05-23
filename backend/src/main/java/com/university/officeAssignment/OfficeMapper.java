package com.university.officeAssignment;

import com.university.instructor.Instructor;
import org.springframework.stereotype.Component;

@Component
public class OfficeMapper {
    public OfficeAssignment toOffice(OfficeRequest request) {
        if (request == null) {
            return null ;
        }
        return OfficeAssignment.builder()
                .id(request.id())
                .location(request.location())
                .instructor(Instructor.builder().id(request.instructorId()).build())
                .build();
    }

    public OfficeResponse fromOffice(OfficeAssignment officeAssignment) {
        return OfficeResponse.builder()
                .id(officeAssignment.getId())
                .location(officeAssignment.getLocation())
                .InstructorName(officeAssignment.getInstructor().getName())
                .build();
    }
}
