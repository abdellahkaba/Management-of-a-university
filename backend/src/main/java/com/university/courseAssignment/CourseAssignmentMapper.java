package com.university.courseAssignment;

import com.university.course.Course;
import com.university.instructor.Instructor;
import org.springframework.stereotype.Component;

@Component
public class CourseAssignmentMapper {
    public CourseAssignment toCourseAssignment(CourseAssignmentRequest request) {
        if (request == null){
            return null ;
        }
        return CourseAssignment.builder()
                .id(request.id())
                .instructor(Instructor.builder().id(request.instructorId()).build())
                .course(Course.builder().id(request.courseId()).build())
                .build();
    }
}
