package com.university.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByContact(String contact);
}
