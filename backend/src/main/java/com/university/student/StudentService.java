package com.university.student;

import com.university.handler.BusinessErrorCodes;
import jakarta.persistence.EntityNotFoundException;
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

    public StudentResponse getStudentById(Integer studentId) {
        return repository.findById(studentId)
                .map(mapper::fromStudent)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + studentId));
    }

}
