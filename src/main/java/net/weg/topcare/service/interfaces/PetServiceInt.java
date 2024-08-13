package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
import net.weg.topcare.entity.Pet;

import java.util.List;

public interface PetServiceInt {
    PetGetRequestDTO postPet(PetPostRequestDTO dto);
    PetGetRequestDTO getOnePet(Long id);
    List<PetGetRequestDTO> getPets();
    Pet patchPet(PetPatchRequestDTO dto, Long id);
    String deletePet(Long id);

}
