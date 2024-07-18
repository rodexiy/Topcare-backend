package net.weg.topcare.controller.dto.produto;

import net.weg.topcare.controller.dto.avaliacao.AvaliacaoGeralGetDTO;
import net.weg.topcare.entity.EspecificacaoProduto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProdutoGetDTO (
        Long id,
        String nome,
        Double preco,
        AvaliacaoGeralGetDTO avaliacaoGeral,
        Integer desconto,
        String descricao,
        List<EspecificacaoProduto> especificacoes,
        List<MultipartFile> imagens
){}
