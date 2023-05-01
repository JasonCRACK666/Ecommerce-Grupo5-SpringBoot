package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.review.CreateReviewDto;
import com.group5.ecommerce.dto.review.UpdateReviewDto;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.review.ReviewResponse;
import com.group5.ecommerce.service.review.ReviewService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "product/{productId}")
    public ResponseEntity<SendListResponse<ReviewResponse>> reviews(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(this.reviewService.getAllProductReviews(productId), HttpStatus.OK);
    }

    @GetMapping(path = "{reviewId}")
    public ResponseEntity<ReviewResponse> detailReview(
            @PathVariable("reviewId") Long reviewId
    ) {
       return new ResponseEntity<>(this.reviewService.detailReview(reviewId), HttpStatus.OK);
    }

    @PostMapping(path = "product/{productId}")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody CreateReviewDto reviewData,
            @RequestAttribute("user") Long userId
    ) {
        return new ResponseEntity<>(
                this.reviewService.createReview(userId, productId, reviewData),
                HttpStatus.OK
        );
    }

    @PutMapping(path = "{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @Valid @RequestBody UpdateReviewDto reviewData,
            @RequestAttribute("user") Long userId
    ) throws UserIsNotOwnerException {
        return new ResponseEntity<>(
                this.reviewService.updateReview(userId, reviewId, reviewData),
                HttpStatus.OK
        );
    }

}
