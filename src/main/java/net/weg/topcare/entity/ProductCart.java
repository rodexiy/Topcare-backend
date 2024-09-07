package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    private Boolean selected;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne()
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Cart cart;
}
