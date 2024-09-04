package net.weg.topcare.controller.dto.pet;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import net.weg.topcare.entity.Image;
import net.weg.topcare.enums.AnimalSize;
import net.weg.topcare.enums.PetGender;

import java.time.LocalDate;

public record PetPostRequestDTO (
//        Image picture,
        @NotEmpty
        String name,
        @NotEmpty
        String breed,
        @NotEmpty
        AnimalSize size,
        PetGender gender,
        @Positive @Digits(integer = 5, fraction = 2)
        Double weight,
        @Past @NotNull
        LocalDate birthdate,
        Long idClient
){
}
