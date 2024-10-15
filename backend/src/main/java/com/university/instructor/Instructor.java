package com.university.instructor;

import com.university.courseAssignment.CourseAssignment;
import com.university.officeAssignment.OfficeAssignment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "_instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String contact;
    private LocalDate hireDate;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseAssignment> courseAssignments;
    @OneToOne(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OfficeAssignment officeAssignment;
}
