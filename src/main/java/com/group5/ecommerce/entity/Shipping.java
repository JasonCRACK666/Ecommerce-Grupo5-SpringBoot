package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shipping")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @Column(name = "time_in_days", nullable = false)
    private Integer timeInDays;

    @OneToMany(mappedBy = "shipping")
    private List<Order> orders;
}
