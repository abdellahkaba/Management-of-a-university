package com.university.enrollment;

import com.university.course.Course;
import com.university.student.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {
    public Enrollment toEnrollment(EnrollmentRequest request) {
        if (request == null) {
            return null;
        }
        return Enrollment.builder()
                .id(request.id())
                .grade(request.grade())
                .student(Student.builder().id(request.studentId()).build())
                .course(Course.builder().id(request.courseId()).build())
                .build();
    }
}
