package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @SequenceGenerator(
            name = "brand_sequence",
            sequenceName = "brand_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "brand_sequence"
    )
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String banner;
}
