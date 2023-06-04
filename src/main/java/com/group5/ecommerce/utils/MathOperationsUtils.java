package com.group5.ecommerce.utils;

import com.group5.ecommerce.entity.Review;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Component
public class MathOperationsUtils {
    public static Integer getAverageScore(List<Review> reviews) {
        if (reviews == null) return 0;

        int sumOfScores = 0;

        for (Review review : reviews) {
            sumOfScores += review.getScore();
        }

        int averageScore = 0;

        try {
            averageScore = sumOfScores / reviews.size();
        } catch (ArithmeticException e) {
            return averageScore;
        }

        return averageScore;
    }

    public static BigDecimal getFinalPrice(BigDecimal originalPrice, Integer discountRate) {
        if (discountRate == null) return originalPrice;

        BigDecimal discount = BigDecimal.valueOf(
                (originalPrice.floatValue() * discountRate) / 100
        );

        BigDecimal finalPrice = originalPrice.subtract(discount);

        NumberFormat formatter = new DecimalFormat("#####");

        return BigDecimal.valueOf(
                Integer.parseInt(formatter.format(finalPrice))
        );
    }
}
