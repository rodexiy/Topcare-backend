package net.weg.topcare.controller.dto.pet;

import net.weg.topcare.enums.AnimalSize;

import java.time.LocalDate;

public record PetPatchRequestDTO(
        String name,
        AnimalSize size,
        Double weight,
        LocalDate birthdate,
        Long idPet
) {}
