package net.weg.topcare.controller.dto.client;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.*;
import net.weg.topcare.entity.Address;

import java.time.LocalDate;
public record ClientPostDTO(
        @NotEmpty
        String name,
        @NotEmpty
        @Email
        String email,
        @Pattern(regexp = "/" +
        "(?=.*/d)" +
        "(?=.*[a-z])" +
        "(?=.*[A-Z])" +
        "(?:.*[$*&@#])" +
                "[0-9a-zA-Z-$*&@#]{8,}/"
        )
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
