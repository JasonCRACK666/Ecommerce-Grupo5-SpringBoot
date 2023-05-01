package com.group5.ecommerce.response.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private String username;
    private String avatar;
    private Integer score;
    private String comment;
    private LocalDateTime createdAt;
}
