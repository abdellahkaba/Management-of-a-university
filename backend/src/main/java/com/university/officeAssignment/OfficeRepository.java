package com.university.officeAssignment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface OfficeRepository extends JpaRepository<OfficeAssignment, Integer> {
    Optional<OfficeAssignment> findByInstructorId(Integer instructorId);
    Optional<OfficeAssignment> findByLocation(String location);
}
