package com.company.docreview.service;

import com.company.docreview.dto.ReviewDTO;
import com.company.docreview.entity.Doctor;
import com.company.docreview.entity.Review;
import com.company.docreview.entity.User;
import com.company.docreview.repository.DoctorRepository;
import com.company.docreview.repository.ReviewRepository;
import com.company.docreview.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    //Fetches all reviews written for a specific doctor.
    public List<ReviewDTO> getReviewsByDoctor(Long doctorId) {
        List<Review> reviews = reviewRepository.findAllByDoctorId(doctorId);
        return reviews.stream()
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getComment(),
                        review.getOverallRating(),
                        review.getCreatedAt(),
                        review.getDoctor().getName(),
                        review.getUser().getName() ,    // show user who submitted the review
                        review.getUser().getId()
                ))
                .toList();
    }
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(review -> new ReviewDTO(
                review.getId(),
                review.getComment(),
                review.getOverallRating(),
                review.getCreatedAt(),
                review.getDoctor().getName(),
                review.getUser().getName(), review.getUser().getId()
        ))
                // Adjust as per your DTO conversion
                .collect(Collectors.toList());
    }


    //Fetches all reviews submitted by a specific user.

    public List<ReviewDTO> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        return reviews.stream()
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getComment(),
                        review.getOverallRating(),
                        review.getCreatedAt(),
                        review.getDoctor().getName(),
                        review.getUser().getName(),review.getUser().getId()
                ))
                .toList();
    }

    //Add a new review.
    @Transactional
    public Review addReview(Long doctorId, Long userId, BigDecimal rating, String comment) {
        //Prevents duplicate reviews from the same user for the same doctor.
        if (reviewRepository.findByDoctorIdAndUserId(doctorId, userId).isPresent()) {
            throw new IllegalStateException("User has already reviewed this doctor.");
        }
        //Loads doctor and user objects or throws an exception if not found.
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        //Populates review fields
        Review review = new Review();
        review.setDoctor(doctor);
        review.setUser(user);
        review.setOverallRating(rating);
        review.setComment(comment);
        review.setCreatedAt(OffsetDateTime.now());
        review.setUpdatedAt(OffsetDateTime.now());

        return reviewRepository.save(review);
    }

    //Update a review
    @Transactional
    public Review updateReview(Long reviewId, Long userId, BigDecimal newRating, String newComment) {
        //Retrieves the review to be updated.
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        //Ensures only the author of the review can update it.
        if (!review.getUser().getId().equals(userId)) {
            throw new SecurityException("You are not allowed to edit this review.");
        }

        //Enforces a time limit: review can only be edited within 24 hours of creation.
        if (OffsetDateTime.now().isAfter(review.getCreatedAt().plusHours(24))) {
            throw new IllegalStateException("Review can only be edited within 24 hours.");
        }

        //Updates the rating and comment, saves it, and returns the updated review.
        review.setOverallRating(newRating);
        review.setComment(newComment);
        review.setUpdatedAt(OffsetDateTime.now());

        return reviewRepository.save(review);
    }

    //Delete a review.
    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new SecurityException("You are not allowed to delete this review.");
        }

        reviewRepository.deleteById(reviewId);
    }
}
