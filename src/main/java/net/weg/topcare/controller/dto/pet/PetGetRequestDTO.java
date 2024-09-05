package net.weg.topcare.controller.dto.pet;

import jakarta.validation.constraints.*;
import net.weg.topcare.entity.Image;
import net.weg.topcare.enums.AnimalSize;
import net.weg.topcare.enums.PetGender;

import java.time.LocalDate;

public record PetGetRequestDTO(
        @NotEmpty @Size(max = 50) @Digits(integer = 5, fraction = 0)
        String name,
        @NotEmpty
        String breed,
        @NotEmpty
        String size,
        @NotEmpty
        String gender,
        @NotEmpty
        Double weight,
        @Past @NotNull
        LocalDate birthdate,
        Image picture,
        @NotNull @Positive
        Long id

) {}
