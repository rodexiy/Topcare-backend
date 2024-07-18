package net.weg.topcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.weg.topcare.enums.StatusDoPedido;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    @ToString.Exclude // fazer DTO
    private Cliente cliente;

    @Enumerated(EnumType.ORDINAL)
    private StatusDoPedido statusDoPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Endereco enderecoEntrega;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<ProdutoPedido> produtosNoPedido;

    @Column(nullable = false)
    private Double total;
}
