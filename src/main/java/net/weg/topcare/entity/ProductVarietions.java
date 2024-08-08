package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationGetDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.controller.dto.productVarietions.PVGetDTO;
import net.weg.topcare.controller.dto.productVarietions.PVPostDTO;
import org.springframework.beans.BeanUtils;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVarietions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    public ProductVarietions(PVPostDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }

    public PVGetDTO toGetDTO() {
        return new PVGetDTO(this.name, this.price, this.stock);
    }

//    {
//        "name": "Ração pequena",
//            "price": 54.50,
//            "stock": 8,
//            "productId": 1
//    }

//    use topcare;
//
//    INSERT INTO brand (name) VALUES ("MARCA 1");
//
//    INSERT INTO product (brand_id, name, description, discount, general_rating, price, stock)
//    VALUES (1, "Bocão", "uma ração perfeita para seu cão", 2, 2, 22.50, 2),
//           (1, "Origins", "a ração dos antigos", 50, 2, 122.50, 10);

}
