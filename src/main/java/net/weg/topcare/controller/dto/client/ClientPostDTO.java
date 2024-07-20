package net.weg.topcare.controller.dto.client;

import net.weg.topcare.entity.Address;

import java.time.LocalDate;

public record ClientPostDTO(
        String name,
        String email,
        String password,
        String cpf,
        String phone,
        Address address,
        LocalDate birthdate
) {}
