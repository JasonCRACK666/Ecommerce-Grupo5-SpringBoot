package com.group5.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wish_list")
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @SequenceGenerator(
            name = "wish_list_sequence",
            sequenceName = "wish_list_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wish_list_sequence"
    )
    private Long id;
}
