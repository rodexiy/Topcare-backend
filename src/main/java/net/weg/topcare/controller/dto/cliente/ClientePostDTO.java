package net.weg.topcare.controller.dto.cliente;

import net.weg.topcare.entity.Endereco;

public record ClientePostDTO (
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone,
        Endereco endereco
) {}
