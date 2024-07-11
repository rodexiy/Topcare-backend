package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(length = 13)
    private String telefone;

    @OneToOne
    private Endereco enderecoPrincipal;

    @OneToMany
    private List<Endereco> enderecos;

    @OneToMany
    private List<Pedido> pedidos;

    @OneToOne
    @JoinColumn(nullable = false)
    private Carrinho carrinho;

    @ManyToMany
    private List<Produto> produtosFavoritos;

    @OneToMany
    private List<Avaliacao> avaliacoes;
}
