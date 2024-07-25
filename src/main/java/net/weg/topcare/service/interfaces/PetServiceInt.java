package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;

import java.util.List;

public interface PetServiceInt {
    Pet postPet(PetPostRequestDTO dto);
    PetGetRequestDTO getOnePet(Long id);
    List<PetGetRequestDTO> getPets();
}
