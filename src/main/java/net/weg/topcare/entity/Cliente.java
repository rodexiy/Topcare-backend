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
    @OneToOne
    private Endereco enderecoPrincipal;

    @OneToMany
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Carrinho carrinho;

    @ManyToMany
    private List<Produto> produtosFavoritos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente") // cascade = CascadeType.DETACH
    private List<Pet> pets;

    @OneToOne
    private Imagem banner;
}
