package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;

public interface PetServiceInt {
    Pet postPet(PetPostRequestDTO dto);
}
