package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.cart.ProductToCartDTO;

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
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    private Boolean selected;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne()
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Cart cart;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Image image;

    public ProductToCartDTO toDto() {
        return new ProductToCartDTO(
                this.id,
                this.amount,
                this.product.getImages().get(0),
                this.product.getName(),
                this.product.getPrice(),
                this.product.getDiscount(),
                this.selected
        );
    }
}
