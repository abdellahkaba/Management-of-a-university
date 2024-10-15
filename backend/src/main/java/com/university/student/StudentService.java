package com.university.student;

import com.university.handler.BusinessErrorCodes;
import io.micrometer.common.util.StringUtils;
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

    public void updateStudent(UpdateStudentRequest request) {
        var student = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + request.id()));
        if (StringUtils.isNotBlank(request.name())){
            student.setName(request.name());
        }
        if (request.dateNaiss() != null){
            student.setDateNaiss(request.dateNaiss());
        }
        repository.save(student);
    }

    public void deleteStudent(Integer studentId) {
        var student = repository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + studentId));
        repository.delete(student);
    }

}
