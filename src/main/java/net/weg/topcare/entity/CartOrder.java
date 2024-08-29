package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.cartorder.CartOrderMaximalGetDTO;
import net.weg.topcare.controller.dto.cartorder.CartOrderMinimalGetDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class CartOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude // fazer DTO
    private Client client;

    @OneToMany
    private List<OrderStatus> orderStatuses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @OneToMany(mappedBy = "cartOrder")
    private List<ProductOrder> products = new ArrayList<>();

    @Column(nullable = false)
    private Double productsTotal;

    @Column(nullable = false)
    private int numberOrder;

    @Column(nullable = false)
    private Double freight;

    @Column(nullable = false)
    private Double discount;

    private LocalDate dateOrderFinished;

    public CartOrderMinimalGetDTO convertToMinimalGetDTO() {
        return new CartOrderMinimalGetDTO(
                this.getClient().getName(),
                this.getOrderStatuses().stream().map(status -> status.getOrderStatus().getNome()).collect(Collectors.joining(", ")),
                (long) this.getNumberOrder(),
                this.getDateOrderFinished()
        );
    }

    public CartOrderMaximalGetDTO convertToMaximalGetDTO() {
        return new CartOrderMaximalGetDTO(
                this.getClient(),
                this.getAddress(),
                this.getOrderStatuses().stream().map(status -> status.getOrderStatus().getNome()).collect(Collectors.joining(", ")),
                (long) this.getNumberOrder(),
                this.getDateOrderFinished(),
                this.getProducts()
        );
    }

}
