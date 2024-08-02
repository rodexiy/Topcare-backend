package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products;

    public Brand(BrandPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
