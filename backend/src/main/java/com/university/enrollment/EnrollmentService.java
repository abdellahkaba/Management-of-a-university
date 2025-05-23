package com.university.enrollment;


import com.university.course.CourseRepository;
import com.university.handler.BusinessErrorCodes;
import com.university.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<EnrollmentResponse> listStudentEnroll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEnrollment)
                .collect(Collectors.toList());
    }

    public EnrollmentResponse getEnrollStudentById(Integer enrollId) {
        return repository.findById(enrollId)
                .map(mapper::fromEnrollment)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + enrollId));
    }

    public void updateEnrollment(UpdateEnrollmentRequest request) {
        var enrollment = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + request.id()));
        if (request.studentId() != null) {
            var student = studentRepository.findById(request.studentId())
                    .orElseThrow(() -> new EntityNotFoundException("Cet Etudiant n'existe pas"));
            enrollment.setStudent(student);
        }
        if (request.courseId() != null) {
            var course = courseRepository.findById(request.courseId())
                    .orElseThrow(() -> new EntityNotFoundException("Ce cours n'existe pas"));
            enrollment.setCourse(course);
        }
        if (request.grade() != null) {
            enrollment.setGrade(request.grade());
        }
        repository.save(enrollment);
    }

    public void deleteEnrollment(Integer enrollId) {
        var enrollment = repository.findById(enrollId)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription()+ " : " + enrollId));
        repository.delete(enrollment);
    }
}
