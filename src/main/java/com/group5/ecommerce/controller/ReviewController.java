package com.group5.ecommerce.controller;

import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.review.ReviewResponse;
import com.group5.ecommerce.service.review.ReviewService;

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

}
