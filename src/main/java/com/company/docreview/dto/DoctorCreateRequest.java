package com.company.docreview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCreateRequest {
    private String name, bio, photoUrl, experience, contactPhone, contactEmail, specialty;
    private Short yearsOfExperience;
    private Long hospitalId;
}
