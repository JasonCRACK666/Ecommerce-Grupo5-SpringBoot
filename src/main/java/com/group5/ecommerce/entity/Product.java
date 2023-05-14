package com.group5.ecommerce.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "original_price", scale = 2, nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(nullable = false)
    private Integer pointValue;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "INT default 0")
    private int sold;

    @CreationTimestamp
    private LocalDateTime publicationDate;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @JdbcTypeCode(SqlTypes.JAVA_OBJECT)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @JdbcTypeCode(SqlTypes.JAVA_OBJECT)
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wish_list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<WishList> wishLists;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "color_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Color> colors;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}
