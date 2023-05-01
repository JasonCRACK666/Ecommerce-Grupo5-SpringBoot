package com.group5.ecommerce.service.review;

import com.group5.ecommerce.entity.Product;
import com.group5.ecommerce.exception.NotFoundException;
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

    private final ProductRepository productRepository;

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

}
