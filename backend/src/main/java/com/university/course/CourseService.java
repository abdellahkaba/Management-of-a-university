package com.university.course;


import com.university.exception.CourseException;
import com.university.handler.BusinessErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;
    public String addCourse(CourseRequest request) {
        if (repository.findByTitleAndDepartmentId(request.title(), request.departmentId()).isPresent()) {
            throw new CourseException(BusinessErrorCodes.DUPLICATE_COURSE_FOR_DEPARTMENT.getDescription());
        }
        var course = mapper.toCourse(request);
        return repository.save(course).getTitle();
    }
}
