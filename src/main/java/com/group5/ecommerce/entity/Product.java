package com.group5.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "discount_price", scale = 2, nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(nullable = false)
    private Integer pointValue;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "in_offer")
    private Boolean inOffer = false;

    @Column(nullable = false)
    private Integer sold;

    @Column(name = "publication_date")
    private Date publicationDate = new Date(System.currentTimeMillis());

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wish_list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Color> colors;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}
