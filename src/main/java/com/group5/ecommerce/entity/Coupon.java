package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "coupon")
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @SequenceGenerator(
            name = "coupon_sequence",
            sequenceName = "coupon_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coupon_sequence"
    )
    private Long id;

    private UUID code = UUID.randomUUID();

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(name = "cost_points", nullable = false)
    private Integer costPoints;
}
