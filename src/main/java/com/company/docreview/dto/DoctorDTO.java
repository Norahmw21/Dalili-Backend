package com.company.docreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//doctor dto
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long id;
    private String name;
    private String bio;
    private String photoUrl;
    private Short yearsOfExperience;
    private String experience;
    private String contactPhone;
    private String contactEmail;
    private String specialty;
}

