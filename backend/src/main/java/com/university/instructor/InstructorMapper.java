package com.university.instructor;

import org.springframework.stereotype.Component;
@Component
public class InstructorMapper {
    public Instructor toInstructor(InstructorRequest request) {
        if (request == null){
            return null ;
        }
        return Instructor.builder()
                .id(request.id())
                .name(request.name())
                .contact(request.contact())
                .hireDate(request.hireDate())
                .build();
    }

    public InstructorResponse fromInstructor(Instructor instructor) {
        return InstructorResponse.builder()
                .id(instructor.getId())
                .name(instructor.getName())
                .contact(instructor.getContact())
                .hireDate(instructor.getHireDate())
                .build();
    }
}
