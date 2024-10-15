package com.university.department;

import com.university.instructor.Instructor;
import org.springframework.stereotype.Component;
@Component
public class DepartmentMapper {
    public Department toDepartment(DepartmentRequest request) {
        if (request == null) {
            return null ;
        }
        return Department.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .administrator(Instructor.builder().id(request.instructorId()).build())
                .build();
    }
}
