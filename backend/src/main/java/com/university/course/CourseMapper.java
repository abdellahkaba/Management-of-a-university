package com.university.course;

import com.university.department.Department;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toCourse(CourseRequest request) {
        if (request == null) {
            return null ;
        }
        return Course.builder()
                .id(request.id())
                .title(request.title())
                .credit(request.credit())
                .department(Department.builder().id(request.departmentId()).build())
                .build();
    }

    public CourseResponse fromCourse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .credit(course.getCredit())
                .departmentName(course.getDepartment().getName())
                .build();
    }
}
