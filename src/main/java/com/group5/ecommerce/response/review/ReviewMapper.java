package com.group5.ecommerce.response.review;

import com.group5.ecommerce.entity.Review;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "review.id", target = "id")
    @Mapping(source = "review.user.userName", target = "username")
    @Mapping(source = "review.user.account.avatar", target = "avatar")
    @Mapping(expression = "java(review.getCreatedAt().toString())", target = "createdAt")
    ReviewResponse toResponse(Review review);

    List<ReviewResponse> toListResponse(List<Review> reviews);

}
