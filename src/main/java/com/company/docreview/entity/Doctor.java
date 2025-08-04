package com.company.docreview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "bio", nullable = false, length = Integer.MAX_VALUE)
    private String bio;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "years_of_experience", nullable = false)
    private Short yearsOfExperience;

    @Column(name = "experience", nullable = false, length = Integer.MAX_VALUE)
    private String experience;

    @Column(name = "contact_phone", nullable = false, length = 20)
    private String contactPhone;

    @Column(name = "contact_email", nullable = false, length = 100)
    private String contactEmail;

    @Column(name = "specialty", nullable = false, length = 50)
    private String specialty;

}