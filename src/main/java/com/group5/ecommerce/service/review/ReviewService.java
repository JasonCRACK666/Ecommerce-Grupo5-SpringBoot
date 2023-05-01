package com.group5.ecommerce.service.review;

import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.review.ReviewResponse;

public interface ReviewService {
    SendListResponse<ReviewResponse> getAllProductReviews(Long productId);
    ReviewResponse detailReview(Long reviewId);
}
