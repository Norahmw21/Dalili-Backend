package com.company.docreview.controller;

import com.company.docreview.dto.ReviewDTO;
import com.company.docreview.entity.Review;
import com.company.docreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //Calls the service to get all reviews for that doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(reviewService.getReviewsByDoctor(doctorId));
    }


    //Calls the service to returns reviews submitted by a specific user.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }


    //Receives JSON input from the frontend
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Map<String, Object> payload) {
        //Extracts each field from the JSON map, converting it to the correct type.
        Long doctorId = Long.valueOf(payload.get("doctorId").toString());
        Long userId = Long.valueOf(payload.get("userId").toString());
        BigDecimal rating = new BigDecimal(payload.get("rating").toString());
        String comment = payload.get("comment").toString();

        //Extracts each field from the JSON map, converting it to the correct type.
        return ResponseEntity.ok(reviewService.addReview(doctorId, userId, rating, comment));
    }

    //Update review
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody Map<String, Object> payload
    ) {
        //New rating, and new comment from the JSON payload.
        Long userId = Long.valueOf(payload.get("userId").toString());
        BigDecimal rating = new BigDecimal(payload.get("rating").toString());
        String comment = payload.get("comment").toString();

        return ResponseEntity.ok(reviewService.updateReview(reviewId, userId, rating, comment));
    }

    //Delete review
    @DeleteMapping("/{reviewId}")
    // Ensures the user is authorized to delete it.
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId, @RequestParam Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }
}

