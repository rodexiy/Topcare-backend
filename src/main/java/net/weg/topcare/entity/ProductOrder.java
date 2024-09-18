package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.weg.topcare.controller.dto.productOrder.ProductOrderPostDTO;
import org.springframework.beans.BeanUtils;

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

    @Column(nullable = false)
    private Integer units;


    @ManyToOne
    private Image image;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private CartOrder cartOrder;

    public ProductOrder(ProductOrderPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
