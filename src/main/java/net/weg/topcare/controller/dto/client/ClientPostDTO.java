package net.weg.topcare.controller.dto.client;

import jakarta.validation.constraints.Pattern;
import net.weg.topcare.entity.Address;

import java.time.LocalDate;

public record ClientPostDTO(
        String name,
        String email,
        @Pattern(regexp = "/" +
        "(?=.*/d)" +
        "(?=.*[a-z])" +
        "(?=.*[A-Z])" +
        "(?:.*[$*&@#])" +
                "[0-9a-zA-Z-$*&@#]{8,}/"
        )
        String password,
        String cpf,
        String phone,
        Address address,
        LocalDate birthdate
) {}
