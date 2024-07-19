package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude // fazer DTO
    private Client client;

    @OneToMany
    private List<OrderStatus> orderStatuses;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<ProductOrder> products;

    @Column(nullable = false)
    private Double total;
}
