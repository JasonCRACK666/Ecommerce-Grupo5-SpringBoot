package com.group5.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @SequenceGenerator(
            name = "cart_item_sequence",
            sequenceName = "cart_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_sequence"
    )
    private Long id;

    private Integer count = 1;
}
