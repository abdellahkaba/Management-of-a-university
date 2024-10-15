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

    public DepartmentResponse fromDepartment(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .administrator(department.getAdministrator().getName())
                .build();
    }
}
