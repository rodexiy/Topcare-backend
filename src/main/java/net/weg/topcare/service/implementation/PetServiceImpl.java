package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.PetRepository;
import net.weg.topcare.service.interfaces.PetServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetServiceInt {
    private final PetRepository repository;
    private final ClientRepository clientRepository;
    @Override
    public Pet postPet(PetPostRequestDTO dto) {
        System.out.printf("dto: " + dto);
        Pet pet = new Pet(dto);
        pet.setClient(clientRepository.findById(dto.idClient()).get());
        System.out.println("Pet: " + pet);
        return repository.save(pet);
    }

    @Override
    public PetGetRequestDTO getOnePet(Long id) {
        Optional<Pet> pet = repository.findById(id);
        return pet.map(Pet::toDto).orElse(null);
    }

    @Override
    public List<PetGetRequestDTO> getPets() {
        List<Pet> pet = repository.findAll();
        return pet.stream().map(Pet::toDto).toList();
    }
}
