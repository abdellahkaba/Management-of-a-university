package com.university.courseAssignment;


import com.university.course.CourseRepository;
import com.university.instructor.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
