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

    public StudentResponse fromStudent(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .dateNaiss(student.getDateNaiss())
                .build();
    }
}