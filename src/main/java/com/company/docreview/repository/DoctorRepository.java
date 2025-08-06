package com.company.docreview.repository;


import com.company.docreview.dto.DoctorWithHospitalDTO;
import com.company.docreview.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Custom query to find doctors by name and/or specialty
    List<Doctor> findByNameContainingIgnoreCaseAndSpecialtyContainingIgnoreCase(String name, String specialty);

    // Custom query to find doctors with an average rating greater than or equal to a given value
    @Query("SELECT d FROM Doctor d JOIN Review r ON d.id = r.doctor.id GROUP BY d.id HAVING AVG(r.overallRating) >= :minRating")
    List<Doctor> findByAverageRatingGreaterThanEqual(@Param("minRating") double minRating);

    // Combining all filters: name, specialty, and average rating
    @Query("SELECT d FROM Doctor d JOIN Review r ON d.id = r.doctor.id WHERE d.name LIKE %:name% AND d.specialty LIKE %:specialty% GROUP BY d.id HAVING AVG(r.overallRating) >= :minRating")
    List<Doctor> findByNameAndSpecialtyAndAverageRating(
            @Param("name") String name,
            @Param("specialty") String specialty,
            @Param("minRating") double minRating
    );

    @Query("SELECT DISTINCT d.specialty FROM Doctor d ORDER BY d.specialty")
    List<String> findDistinctSpecialties();


    @Query("""
            SELECT new com.company.docreview.dto.DoctorWithHospitalDTO(
              d.id, d.name, d.specialty, d.yearsOfExperience,
              d.contactPhone, d.contactEmail, d.photoUrl,
              h.id, h.name, h.latitude, h.longitude
            )
            FROM DoctorHospital dh
            JOIN dh.doctor d
            JOIN dh.hospital h
            WHERE (:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', CAST(:name AS text), '%')))
            AND (:specialty IS NULL OR LOWER(d.specialty) LIKE LOWER(CONCAT('%', CAST(:specialty AS text), '%')))
            AND (:hospitalId IS NULL OR h.id = :hospitalId)
            """)
    List<DoctorWithHospitalDTO> findDoctorsWithHospitals(
            @Param("name") String name,
            @Param("specialty") String specialty,
            @Param("hospitalId") Long hospitalId
    );
    @Query("""
    SELECT new com.company.docreview.dto.DoctorWithHospitalDTO(
        d.id, d.name, d.specialty, d.yearsOfExperience,
        d.contactPhone, d.contactEmail, d.photoUrl,
        h.id, h.name, h.latitude, h.longitude
    )
    FROM DoctorHospital dh
    JOIN dh.doctor d
    JOIN dh.hospital h
""")
    List<DoctorWithHospitalDTO> findDoctorsWithHospitals();


}