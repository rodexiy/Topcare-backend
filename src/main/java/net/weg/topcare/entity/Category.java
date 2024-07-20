package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> productsInCategory;

    public Category(CategoryPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
