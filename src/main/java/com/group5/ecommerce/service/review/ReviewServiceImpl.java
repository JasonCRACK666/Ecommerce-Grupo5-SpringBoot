package com.group5.ecommerce.service.review;

import com.group5.ecommerce.dto.review.CreateReviewDto;
import com.group5.ecommerce.dto.review.UpdateReviewDto;
import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.entity.Review;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.repository.ReviewRepository;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.repository.product.ProductRepository;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.review.ReviewMapper;
import com.group5.ecommerce.response.review.ReviewResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public SendListResponse<ReviewResponse> getAllProductReviews(Long productId) {
        Product product = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new NotFoundException("El producto no existe")
                );

        List<ReviewResponse> reviewResponses = ReviewMapper.INSTANCE.toListResponse(product.getReviews());

        return new SendListResponse<>(reviewResponses);
    }

    @Override
    public ReviewResponse detailReview(Long reviewId) {
        var review = this.reviewRepository
                .findById(reviewId)
                .orElseThrow(
                        () -> new NotFoundException("La reseña no existe")
                );

        return ReviewMapper.INSTANCE.toResponse(review);
    }

    @Override
    public ReviewResponse createReview(Long userId, Long productId, CreateReviewDto reviewData) {
        var user = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NotFoundException("La cuenta no existe")
                );

        var product = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new NotFoundException("El producto no existe")
                );

        var review = Review.builder()
                .score(reviewData.getScore())
                .comment(reviewData.getComment())
                .user(user)
                .product(product)
                .build();

        var savedReview = this.reviewRepository.save(review);

        return ReviewMapper.INSTANCE.toResponse(savedReview);
    }

    @Override
    public ReviewResponse updateReview(Long userId, Long reviewId, UpdateReviewDto reviewData) throws UserIsNotOwnerException {
        var user = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NotFoundException("La cuenta no existe")
                );

        var review = this.reviewRepository
                .findById(reviewId)
                .orElseThrow(
                        () -> new NotFoundException("La reseña no existe")
                );

        if (!review.getUser().getId().equals(user.getId()))
            throw new UserIsNotOwnerException("Usted no es el dueño de esta reseña");

        review.setScore(reviewData.getScore());
        review.setComment(reviewData.getComment());

        var savedReview = this.reviewRepository.save(review);

        return ReviewMapper.INSTANCE.toResponse(savedReview);
    }

}
