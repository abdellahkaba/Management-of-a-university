package com.university.officeAssignment;

import com.university.exception.InstructorOfficeConflictException;
import com.university.exception.LocationConflictException;
import com.university.handler.BusinessErrorCodes;
import com.university.instructor.InstructorRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository repository;
    private final OfficeMapper mapper;
    private final InstructorRepository instructorRepository;
    public String createOffice(OfficeRequest request) {
        var instructor = instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new EntityNotFoundException("Cet Instructeur n'existe pas")) ;
        if (repository.findByLocation(request.location()).isPresent()){
            throw new LocationConflictException(BusinessErrorCodes.DUPLICATE_LOCATION_OFFICE.getDescription() + " : " + request.location());
        }
        if (repository.findByInstructorId(request.instructorId()).isPresent()){
            throw new InstructorOfficeConflictException(BusinessErrorCodes.DUPLICATE_INSTRUCTOR_FOR_OFFICE.getDescription());
        }
        var office = mapper.toOffice(request) ;
        office.setInstructor(instructor);
        return repository.save(office).getLocation();
    }

    public void updateOffice(UpdateOfficeRequest request) {
        var office = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + request.id()));
        if (StringUtils.isNotBlank(request.location())){
            office.setLocation(request.location());
        }
        if (request.instructorId() != null){
            var instructor = instructorRepository.findById(request.instructorId())
                    .orElseThrow(() -> new EntityNotFoundException("Cet Instructeur n'existe pas"));
            office.setInstructor(instructor);
        }
        repository.save(office);
    }
}
