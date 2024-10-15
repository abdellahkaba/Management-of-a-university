package com.university.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository ;
    private final StudentMapper mapper ;
    public Integer newStudent(StudentRequest request) {
        var student = mapper.toStudent(request);
        return repository.save(student).getId() ;
    }
}
