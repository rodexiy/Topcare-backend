package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Product> productsInCategory = new ArrayList<>();

    public Category(CategoryPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public CategoryGetDTO toGetDTO(){
        return new CategoryGetDTO(
                this.id,
                this.name,
                this.productsInCategory
        );
    }
}
