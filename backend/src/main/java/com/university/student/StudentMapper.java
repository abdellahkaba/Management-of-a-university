package com.university.student;

import org.springframework.stereotype.Component;
@Component
public class StudentMapper {
    public Student toStudent(StudentRequest request) {
        if (request == null) {
            return null ;
        }
        return Student.builder()
                .id(request.id())
                .name(request.name())
                .dateNaiss(request.dateNaiss())
                .build();
    }
}