package com.university.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.title = :title AND c.department.id = :departmentId")
    Optional<Course> findByTitleAndDepartmentId(@Param("title") String title, @Param("departmentId") Integer departmentId);
}
