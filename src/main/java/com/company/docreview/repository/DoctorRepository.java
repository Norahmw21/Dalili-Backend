package com.company.docreview.repository;


import com.company.docreview.entity.Doctor;
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

}