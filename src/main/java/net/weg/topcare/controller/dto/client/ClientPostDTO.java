package net.weg.topcare.controller.dto.client;

import jakarta.validation.constraints.*;
import net.weg.topcare.entity.Address;

import java.time.LocalDate;

public record ClientPostDTO(
        @NotEmpty
        String name,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password,
        @NotEmpty
        String cpf,
        @NotEmpty
        String phone,
        @NotNull
        Address address,
        @Past
        @NotNull
        LocalDate birthdate
) {}
