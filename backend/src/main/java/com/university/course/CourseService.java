package com.university.course;


import com.university.department.DepartmentRepository;
import com.university.exception.CourseException;
import com.university.handler.BusinessErrorCodes;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;
    private final DepartmentRepository departmentRepository;
    public String addCourse(CourseRequest request) {
        if (repository.findByTitleAndDepartmentId(request.title(), request.departmentId()).isPresent()) {
            throw new CourseException(BusinessErrorCodes.DUPLICATE_COURSE_FOR_DEPARTMENT.getDescription());
        }
        var course = mapper.toCourse(request);
        return repository.save(course).getTitle();
    }

    public List<CourseResponse> listCourse() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCourse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Integer courseId) {
        return repository.findById(courseId)
                .map(mapper::fromCourse)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + courseId));
    }

    public void updateCourse(UpdateCourseRequest request) {
        var course = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + request.id()));
        if (StringUtils.isNotBlank(request.title())){
            course.setTitle(request.title());
        }
        if (request.credit() > 0){
            course.setCredit(request.credit());
        }
        if (request.departmentId() != null){
            var department = departmentRepository.findById(request.departmentId())
                    .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription()));
            course.setDepartment(department);
        }
        repository.save(course);
    }

    public void deleteCourse(Integer courseId) {
        var course = repository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(BusinessErrorCodes.ENTITY_NOT_FOUND.getDescription() + " : " + courseId));
        repository.delete(course);
    }
}
