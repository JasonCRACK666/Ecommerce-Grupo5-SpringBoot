package com.group5.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "coupon_base")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponBase {
    @Id
    @SequenceGenerator(
            name = "coupon_base_sequence",
            sequenceName = "coupon_base_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coupon_base_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer discountRate;

    @Column(name = "cost_points", nullable = false)
    private Integer costPoints;

    @OneToMany(mappedBy = "couponBase")
    private List<CouponGenerated> couponGenerates;
}
