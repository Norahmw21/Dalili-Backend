package com.company.docreview.repository;

import com.company.docreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Check if user already reviewed a doctor
    Optional<Review> findByDoctorIdAndUserId(Long doctorId, Long userId);

    // Get reviews by doctor
    List<Review> findAllByDoctorId(Long doctorId);

    // Get reviews by user
    List<Review> findAllByUserId(Long userId);
}