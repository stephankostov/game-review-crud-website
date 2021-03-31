package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.Review;
import com.fdmgroup.project_gamesdatabase.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/reviews/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("get/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable("reviewId") Long reviewId) {
        Optional<Review> review = reviewService.retrieve(reviewId);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(review.get());
    }

    @GetMapping("all")
    public ResponseEntity<List<Review>> allReviews() {
        List<Review> allReviews = reviewService.retrieveAll();
        return ResponseEntity.ok(allReviews);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createReview(@RequestBody Review review) {
        Long reviewId = reviewService.create(review);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(reviewId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long reviewId,
                                                     @RequestBody Review review) {
        Optional<Review> updatedReview = reviewService.update(review);
        if (updatedReview.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReview.get());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") long id) {
        reviewService.delete(id);
        LOGGER.info("Review deleted");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

    