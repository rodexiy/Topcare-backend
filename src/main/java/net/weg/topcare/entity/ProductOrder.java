package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Utilizada no pedido para salvar as informações do produto no momento que foi comprado pelo cliente, caso
// o produto seja editado para outro completamente diferente.

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Image picture;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private CartOrder order;
}
