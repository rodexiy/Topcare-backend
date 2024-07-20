package net.weg.topcare.controller.dto.pet;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import net.weg.topcare.entity.Image;
import net.weg.topcare.enums.AnimalSize;
import net.weg.topcare.enums.PetGender;

import java.time.LocalDate;

public record PetPostRequestDTO (
//        Image picture,
        String name,
        String breed,
        AnimalSize size,
        PetGender gender,
        Double weight,
        LocalDate birthdate
){
}
