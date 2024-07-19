package net.weg.topcare.controller.dto.client;

import net.weg.topcare.entity.*;

import java.time.LocalDate;
import java.util.List;

public record ClienteGetDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        Address mainAddress,
        List<Address> address,
        Cart cart,
        List<Product> favoriteProducts,
        List<Pet> pets,
        Image banner,
        Image profilePicture,
        LocalDate birthDate
) {}
