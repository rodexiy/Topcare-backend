package net.weg.topcare.controller.dto.produto;

import net.weg.topcare.controller.dto.avaliacao.AvaliacaoGeralGetDTO;
import net.weg.topcare.entity.Imagem;

public record ProdutoMinimalGetDTO(
        Long id,
        String nome,
        Double preco,
        Integer desconto,
        Imagem imagem,
        AvaliacaoGeralGetDTO avaliacaoGeral
) {}
