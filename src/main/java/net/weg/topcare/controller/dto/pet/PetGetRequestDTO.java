package net.weg.topcare.controller.dto.pet;

import net.weg.topcare.entity.Image;
import net.weg.topcare.enums.AnimalSize;
import net.weg.topcare.enums.PetGender;

import java.time.LocalDate;

public record PetGetRequestDTO(

        String name,
        String breed,
        String size,
        String gender,
        Double weight,
        LocalDate birthdate,
        Image picture,
        Long id

) {}
