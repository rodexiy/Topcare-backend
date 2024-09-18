package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
import net.weg.topcare.entity.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetServiceInt {
    PetGetRequestDTO postPet(PetPostRequestDTO dto, MultipartFile image);
    PetGetRequestDTO getOnePet(Long id);
    List<PetGetRequestDTO> getPets();
    Pet patchPet(PetPatchRequestDTO dto, Long id, MultipartFile file);
    String deletePet(Long id);

}
