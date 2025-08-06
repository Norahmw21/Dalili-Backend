package com.company.docreview.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopDoctorDto {
    // Setters
    // Getters
    private Long id;
    private String name;
    private String specialty;
    private String photoUrl;
    private Double averageRating;

    public TopDoctorDto(Long id, String name, String specialty, String photoUrl, Double averageRating) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.photoUrl = photoUrl;
        this.averageRating = averageRating;
    }

}
