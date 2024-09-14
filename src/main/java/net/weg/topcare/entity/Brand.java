package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
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
    @Column(length = 30)
    private String name;
    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
    @OneToOne
    private Image image;
    private Integer generalRating;
    public Brand(BrandPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
