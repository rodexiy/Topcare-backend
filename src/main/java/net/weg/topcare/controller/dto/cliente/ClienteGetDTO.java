package net.weg.topcare.controller.dto.cliente;

import net.weg.topcare.entity.*;

import java.time.LocalDate;
import java.util.List;

public record ClienteGetDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        Endereco mainAddress,
        List<Endereco> address,
        Carrinho cart,
        List<Produto> favoriteProducts,
        List<Pet> pets,
        Imagem banner,
        Imagem profilePicture,
        LocalDate birthDate
) {}
