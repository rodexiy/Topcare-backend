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

    @ManyToOne
    private Marca marca;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToMany
    @Column(nullable = false)
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "produto")
    private List<PropriedadeProduto> propriedades;

    @OneToMany
    private List<Imagem> imagens;

    @Column(nullable = false)
    private Integer desconto;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoque;

    @OneToMany(mappedBy = "produto")
    private List<Avaliacao> avaliacoes;

}
