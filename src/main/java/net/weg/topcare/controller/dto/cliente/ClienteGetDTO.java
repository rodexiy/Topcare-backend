package net.weg.topcare.controller.dto.cliente;

import net.weg.topcare.entity.*;

import java.util.List;

public record ClienteGetDTO(
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco enderecoPrincipal,
        List<Endereco> enderecos,
        Carrinho carrinho,
        List<Produto> produtosFavoritos,
        List<Pet> pets,
        Imagem banner,
        Imagem fotoPerfil
) {}
