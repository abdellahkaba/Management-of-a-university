package com.university.department;


import com.university.exception.NameConflictException;
import com.university.handler.BusinessErrorCodes;
import com.university.instructor.InstructorRepository;
import io.micrometer.common.util.StringUtils;
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
    private final InstructorRepository instructorRepository;
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

    public void updateDepartment(UpdateDepartmentRequest request) {
        var department = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + request.id()));
        if (StringUtils.isNotBlank(request.name()) &&
                !request.name().equals(department.getName()) &&
                repository.findByName(request.name()).isPresent()
        ){
            throw new NameConflictException(BusinessErrorCodes.DUPLICATE_NAME.getDescription() + " : " + request.name());
        }
        if (StringUtils.isNotBlank(request.name())){
            department.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())){
            department.setDescription(request.description());
        }
        if (request.instructorId() != null){
            var instructor = instructorRepository.findById(request.instructorId())
                    .orElseThrow(() -> new EntityNotFoundException("Ce Instructor n'existe pas"));
            department.setAdministrator(instructor);
            repository.save(department);
        }
    }

    public void deleteDepartment(Integer departmentId) {
        var department = repository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + departmentId));
        repository.delete(department);
    }
}
