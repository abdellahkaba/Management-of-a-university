package com.university.department;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponse {
    private Integer id;
    private String name;
    private String description;
    private String administrator;
}
