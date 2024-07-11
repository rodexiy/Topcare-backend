package net.weg.topcare.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToMany
    @Column(nullable = false)
    private List<Categoria> categorias;

    @OneToMany
    private List<PropriedadeProduto> propriedades;

    @OneToMany
    private List<Imagem> imagens;

    @Column(nullable = false)
    private Integer desconto;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoque;

    @OneToMany
    private List<Avaliacao> avaliacoes;

}
