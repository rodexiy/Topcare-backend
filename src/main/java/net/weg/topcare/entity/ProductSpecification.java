package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationGetDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String value;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public ProductSpecificationGetDTO toGetDTO() {
        return new ProductSpecificationGetDTO(this.id, this.name, this.value);
    }
}
