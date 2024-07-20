package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.repository.PetRepository;
import net.weg.topcare.service.interfaces.PetServiceInt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetServiceInt {
    private final PetRepository repository;
    @Override
    public Pet postPet(PetPostRequestDTO dto) {
        System.out.printf("dto: " + dto);
        Pet pet = new Pet(dto);
        return repository.save(pet);
    }
}
