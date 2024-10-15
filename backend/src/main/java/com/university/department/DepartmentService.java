package com.university.department;


import com.university.exception.NameConflictException;
import com.university.handler.BusinessErrorCodes;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository ;
    private final DepartmentMapper mapper ;
    public String createDepartment(DepartmentRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new NameConflictException(BusinessErrorCodes.DUPLICATE_NAME.getDescription() + " : " + request.name());
        }
        var department = mapper.toDepartment(request);
        return repository.save(department).getName();
    }

    public List<DepartmentResponse> getAllDepartment() {
        return repository.findAll()
                .stream()
                .map(mapper::fromDepartment)
                .collect(Collectors.toList()) ;
    }

    public DepartmentResponse getDepartmentById(Integer departmentId) {
        return repository.findById(departmentId)
                .map(mapper::fromDepartment)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription()+ " : " + departmentId));
    }
}
