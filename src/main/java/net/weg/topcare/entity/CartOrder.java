package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.weg.topcare.controller.dto.cartorder.CartOrderMaximalGetDTO;
import net.weg.topcare.controller.dto.cartorder.CartOrderMinimalGetDTO;
import net.weg.topcare.enums.PaymentMethod;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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

    @OneToMany(mappedBy = "cartOrder")
    private List<OrderStatus> orderStatuses = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @CreationTimestamp
    private LocalDateTime orderCreated;

    @OneToMany(mappedBy = "cartOrder", cascade = CascadeType.ALL)
    private List<ProductOrder> products = new ArrayList<>();

    @Column(nullable = false)
    private Double productsTotal;

    @Column(nullable = false)
    private int numberOrder;

    @Column(nullable = false)
    private Double freight;

    @Column(nullable = false)
    private Double discount = 0.0;

    private LocalDate dateOrderFinished;

    public Double getCartTotalDiscountAmount() {
        return this.products.stream().mapToDouble(productOrder -> productOrder.getProduct().getDiscountedAmount()).sum();
    }

    public Double getCartTotalDiscounted() {
        return this.products.stream().mapToDouble(productOrder -> productOrder.getProduct().getDiscountedPrice()).sum();
    }

    public Double getCartTotalNotDiscounted() {
        return this.products.stream().mapToDouble(productOrder -> productOrder.getProduct().getPrice()).sum();
    }

    public CartOrderMinimalGetDTO convertToMinimalGetDTO() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new CartOrderMinimalGetDTO(
                this.id,
                this.getClient().getName(),
                this.getOrderStatuses().get(this.getOrderStatuses().size() - 1).getOrderStatus().getNome(),
                this.paymentMethod.getNome(),
                this.getOrderCreated().format(dateTimeFormatter)
        );
    }

    public CartOrderMaximalGetDTO convertToMaximalGetDTO() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new CartOrderMaximalGetDTO(
                this.id,
                this.freight,
                this.getCartTotalDiscountAmount(),
                this.getPaymentMethod().getNome(),
                this.getAddress(),
                this.getOrderStatuses().get(this.getOrderStatuses().size() - 1).getOrderStatus().getNome(),
                (long) this.getNumberOrder(),
                this.getOrderCreated().format(dateTimeFormatter),
                this.getProducts(),
                this.getOrderStatuses().stream().map(orderStatus -> orderStatus.getChangedTime().format(dateTimeFormatter)).toList()
        );
    }

}
