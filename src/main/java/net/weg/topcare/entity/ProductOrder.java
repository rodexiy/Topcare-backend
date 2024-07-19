package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(nullable = false)
    private CartOrder order;
}
