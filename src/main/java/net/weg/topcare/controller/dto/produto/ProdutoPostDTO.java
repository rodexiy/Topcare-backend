package net.weg.topcare.controller.dto.produto;

import net.weg.topcare.controller.dto.avaliacao.AvaliacaoGeralGetDTO;
import net.weg.topcare.entity.Categoria;
import net.weg.topcare.entity.EspecificacaoProduto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProdutoPostDTO (
        String nome,
        String descricao,
        List<Categoria> categorias,
        List<EspecificacaoProduto> especificacoes,
        List<MultipartFile> imagens,
        Integer desconto,
        Double preco,
        Integer estoque
) {}
