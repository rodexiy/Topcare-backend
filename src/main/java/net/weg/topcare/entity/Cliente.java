package net.weg.topcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Endereco enderecoPrincipal;

    @OneToMany
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos;

    @OneToOne
    @JoinColumn(nullable = false)
    private Carrinho carrinho;

    @ManyToMany
    private List<Produto> produtosFavoritos;

    @OneToMany(mappedBy = "cliente")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "cliente")
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente")
    private List<Pet> pets;
}
