package com.group5.ecommerce.service.review;

import com.group5.ecommerce.dto.review.CreateReviewDto;
import com.group5.ecommerce.dto.review.UpdateReviewDto;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.review.ReviewResponse;

public interface ReviewService {
    SendListResponse<ReviewResponse> getAllProductReviews(Long productId);
    ReviewResponse detailReview(Long reviewId);
    ReviewResponse createReview(Long userId, Long productId, CreateReviewDto reviewData);
    MessageResponse deleteReview(Long userId, Long reviewId) throws UserIsNotOwnerException;
    ReviewResponse updateReview(Long userId, Long reviewId, UpdateReviewDto reviewData) throws UserIsNotOwnerException;
}
