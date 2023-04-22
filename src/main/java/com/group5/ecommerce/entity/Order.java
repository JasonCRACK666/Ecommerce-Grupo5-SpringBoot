package com.group5.ecommerce.entity;

import com.group5.ecommerce.entity.enums.OrderStatus;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;

    @Column(scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "postal_zip_code", nullable = false)
    private Integer postalZipCode;

    @Column(name = "shipping_name", nullable = false)
    private String shippingName;

    @Column(name = "shipping_price", nullable = false, scale = 2)
    private BigDecimal shippingPrice;

    @Column(name = "shipping_time", nullable = false)
    private Integer shippingTime;

    @Column(name = "date_issued")
    private Date dateIssued = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "order")
    private CouponGenerated coupon;

    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false)
    private Shipping shipping;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
