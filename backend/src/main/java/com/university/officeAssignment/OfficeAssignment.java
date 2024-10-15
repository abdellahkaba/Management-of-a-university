package com.university.officeAssignment;

import com.university.instructor.Instructor;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "_officeAssignment")
public class OfficeAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String location;

    @OneToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}

