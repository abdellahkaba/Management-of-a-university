package com.university.department;


import com.university.exception.NameConflictException;
import com.university.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
