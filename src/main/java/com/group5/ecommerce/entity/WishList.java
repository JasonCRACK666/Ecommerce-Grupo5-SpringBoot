package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToOne(mappedBy = "wish_list")
    private User user;

    @ManyToMany(mappedBy = "wish_lists")
    private List<Product> products;
}
