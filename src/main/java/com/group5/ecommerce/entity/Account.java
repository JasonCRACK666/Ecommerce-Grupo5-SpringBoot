package com.group5.ecommerce.entity;

import com.group5.ecommerce.entity.enums.Theme;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;

    private String avatar;
    private String banner;

    @Column(columnDefinition = "INT default 0")
    private Integer points = 0;

    @Enumerated(EnumType.STRING)
    private Theme theme = Theme.LIGHT;

    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToOne(mappedBy = "account")
    private User user;
}
