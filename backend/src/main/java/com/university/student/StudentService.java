package com.university.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository ;
    private final StudentMapper mapper ;
    public Integer newStudent(StudentRequest request) {
        var student = mapper.toStudent(request);
        return repository.save(student).getId() ;
    }

    public List<StudentResponse> listAllStudent() {
        return repository.findAll()
                .stream()
                .map(mapper::fromStudent)
                .collect(Collectors.toList());
    }

}
