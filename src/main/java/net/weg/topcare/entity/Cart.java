package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ProductCart> productsInCart = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    private Client client;

    @OneToOne
    private Address selectedAddress;


    public Double getCartTotalDiscountAmount() {
        return this.productsInCart.stream().mapToDouble(productCart -> productCart.getProduct().getDiscountedAmount()).sum();
    }

    public Double getCartTotalDiscounted() {
        return this.productsInCart.stream().mapToDouble(productCart -> productCart.getProduct().getDiscountedPrice()).sum();
    }

    public Double getCartTotalNotDiscounted() {
        return this.productsInCart.stream().mapToDouble(productCart -> productCart.getProduct().getPrice() * productCart.getAmount()).sum();
    }
}
