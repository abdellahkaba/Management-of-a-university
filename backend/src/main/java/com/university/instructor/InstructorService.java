package com.university.instructor;

import com.university.exception.ContactConflictException;
import com.university.handler.BusinessErrorCodes;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository repository;
    private final InstructorMapper mapper;
    public Integer addInstructor(InstructorRequest request) {
        if (repository.findByContact(request.contact()).isPresent()){
            throw new ContactConflictException(BusinessErrorCodes.DUPLICATE_CONTACT.getDescription() + " : " + request.contact());
        }
        var instructor = mapper.toInstructor(request);
        return repository.save(instructor).getId();
    }

    public List<InstructorResponse> listAllInstructor() {
        return repository.findAll()
                .stream()
                .map(mapper::fromInstructor)
                .collect(Collectors.toList());
    }

    public void deleteInstructor(Integer instructorId) {
        var instructor = repository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + instructorId));
        repository.delete(instructor);
    }

    public InstructorResponse getInstructorById(Integer instructorId) {
        return repository.findById(instructorId)
                .map(mapper::fromInstructor)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + instructorId)) ;
    }

    public void updateInstructor(InstructorUpdateRequest request) {
        var instructor = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + ": " + request.id()));
        if (StringUtils.isNotBlank(request.contact()) &&
                !request.contact().equals(instructor.getContact()) &&
                repository.findByContact(request.contact()).isPresent()
        ) {
            throw new ContactConflictException(BusinessErrorCodes.DUPLICATE_CONTACT.getDescription());
        }
        if (StringUtils.isNotBlank(request.name())){
            instructor.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.contact())){
            instructor.setContact(request.contact());
        }
        if (request.hireDate() != null){
            instructor.setHireDate(request.hireDate());
        }
        repository.save(instructor);
    }
}
