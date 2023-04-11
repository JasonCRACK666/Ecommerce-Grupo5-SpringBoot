package com.group5.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "discount_rate", scale = 2, nullable = false)
    private double originalPrice;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer sold;

    @Column(name = "publication_date")
    private Date publicationDate = new Date(System.currentTimeMillis());
}
