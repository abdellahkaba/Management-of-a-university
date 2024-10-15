package com.university.enrollment;


import com.university.course.CourseRepository;
import com.university.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public Integer enrollStudent(EnrollmentRequest request) {
        var student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Cet Etudiant n'existe pas"));
        var course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Ce cours n'existe pas"));
        var enrollment = mapper.toEnrollment(request) ;
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return repository.save(enrollment).getId();
    }
}
