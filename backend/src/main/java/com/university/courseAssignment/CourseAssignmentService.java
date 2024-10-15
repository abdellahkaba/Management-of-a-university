package com.university.courseAssignment;


import com.university.course.CourseRepository;
import com.university.handler.BusinessErrorCodes;
import com.university.instructor.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseAssignmentService {
    private final CoursAssignmentRepository repository ;
    private final CourseAssignmentMapper mapper ;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    public Integer assignCourseInstructor(CourseAssignmentRequest request) {
        var instructor = instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new EntityNotFoundException("Le departement non trouvé"));
        var course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Ce cours n'existe pas"));
        var courseAssignment = mapper.toCourseAssignment(request);
        courseAssignment.setInstructor(instructor);
        courseAssignment.setCourse(course);
        return repository.save(courseAssignment).getId();
    }
    public List<CourseAssignmentResponse> listCourseInstructors() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCourseAssignment)
                .collect(Collectors.toList());
    }

    public CourseAssignmentResponse getCourseAssign(Integer assignmentId) {
        return repository.findById(assignmentId)
                .map(mapper::fromCourseAssignment)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + assignmentId));
    }

    public void deleteCourseAssign(Integer assignmentId) {
        var courseAssignment = repository.findById(assignmentId)
                .orElseThrow(() -> new  EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription()));
        repository.delete(courseAssignment);
    }
}
