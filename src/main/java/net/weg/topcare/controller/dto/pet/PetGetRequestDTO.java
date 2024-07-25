package net.weg.topcare.controller.dto.pet;

import net.weg.topcare.enums.AnimalSize;
import net.weg.topcare.enums.PetGender;

import java.time.LocalDate;

public record PetGetRequestDTO(
        //        Image picture,
        String name,
        String breed,
        AnimalSize size,
        PetGender gender,
        Double weight,
        LocalDate birthdate

) {}
