package com.group5.ecommerce.entity;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Color {
    @Id
    @SequenceGenerator(
            name = "color_sequence",
            sequenceName = "color_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "color_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 7)
    private String hex;

    @ManyToMany(mappedBy = "colors")
    private List<Product> products;

}
