package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    private Boolean selected;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Cart cart;
}
