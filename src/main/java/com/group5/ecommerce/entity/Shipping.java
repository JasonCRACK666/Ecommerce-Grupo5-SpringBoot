package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping")
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    @SequenceGenerator(
            name = "shipping_sequence",
            sequenceName = "shipping_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shipping_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, scale = 2)
    private double price;

    @Column(name = "time_in_days", nullable = false)
    private Integer timeInDays;
}
