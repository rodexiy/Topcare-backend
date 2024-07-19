package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    private Integer generalRating;

    @ManyToMany
    @Column(nullable = false)
    private List<Category> categories;

    @OneToMany(mappedBy = "product")
    private List<ProductSpecification> specifications;

    @OneToMany
    private List<Image> images;

    @Column
    private Integer discount = 0;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratings;



}
