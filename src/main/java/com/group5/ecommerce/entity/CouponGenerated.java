package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "coupon_generated")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponGenerated {
    @Id
    @SequenceGenerator(
            name = "coupon_generated_sequence",
            sequenceName = "coupon_generated_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coupon_generated_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private UUID code;

    @OneToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "coupon_base_id", nullable = false)
    private CouponBase couponBase;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
