package net.weg.topcare.controller.dto.cliente;

import net.weg.topcare.entity.*;

import java.time.LocalDate;
import java.util.List;

public record ClienteGetDTO(
        Long id,
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
        Imagem fotoPerfil,
        LocalDate dataNascimento
) {}
