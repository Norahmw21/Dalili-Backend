package com.company.docreview.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter

public class DoctorWithHospitalDTO {

    private Long doctorId;
    private String doctorName;
    private String specialty;
    private int yearsOfExperience;
    private String contactPhone;
    private String contactEmail;
    private String photoUrl;
    private String bio;
    private String experience;
    private Long hospitalId;
    private String hospitalName;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public DoctorWithHospitalDTO(
            Long doctorId, String doctorName, String specialty, int yearsOfExperience,
            String contactPhone, String contactEmail, String photoUrl,String bio, String experience,
            Long hospitalId, String hospitalName, BigDecimal latitude, BigDecimal longitude
    ) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.experience = experience;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
