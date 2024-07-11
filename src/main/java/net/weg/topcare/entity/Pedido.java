package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Endereco enderecoEntrega;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<ProdutoPedido> produtosNoPedido;

    @Column(nullable = false)
    private Integer total;
}
