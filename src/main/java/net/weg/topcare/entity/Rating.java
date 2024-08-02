package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.rating.GeneralRatingPostDTO;
import org.springframework.beans.BeanUtils;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Client client;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Product product;

    public Rating(GeneralRatingPostDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
