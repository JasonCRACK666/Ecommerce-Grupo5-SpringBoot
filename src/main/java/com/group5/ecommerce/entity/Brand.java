package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_brand",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "brandsFollowing")
    private List<User> followers;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

}
