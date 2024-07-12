package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    private Boolean selecionado;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Carrinho carrinho;
}
