package com.university.instructor;

import com.university.exception.ContactConflictException;
import com.university.handler.BusinessErrorCodes;
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
}
