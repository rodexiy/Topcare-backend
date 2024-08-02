package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
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
    @Override
    public PetGetRequestDTO postPet(PetPostRequestDTO dto) {
        System.out.printf("dto: " + dto);
        Pet pet = new Pet(dto);
        Client client = new Client();
        client.setId(dto.idClient());
        pet.setClient(client);
        System.out.println("Pet: " + pet);
        return repository.save(pet).toDto();
    }

    @Override
    public PetGetRequestDTO getOnePet(Long id) {
        Optional<Pet> pet = repository.findById(id);
        if(pet.get().getAble()){
            return pet.map(Pet::toDto).orElse(null);

        }
        throw new RuntimeException("Pet não existe");
    }

    @Override
    public List<PetGetRequestDTO> getPets() {
        List<Pet> pet = repository.findAll();
        return pet.stream().filter(pet1 -> pet1.getAble() == true).map(Pet::toDto).toList();
    }

    @Override
    public Pet patchPet(PetPatchRequestDTO dto, Long id) {
        if(repository.existsById(id)){
            Pet pet = repository.findById(id).get();

            if(pet.getAble()){
                pet.setName(dto.name());
                pet.setSize(dto.size());
                pet.setBirthdate(dto.birthdate());
                pet.setWeight(dto.weight());
                return repository.save(pet);
            }
            throw new RuntimeException("Pet deletado");
        }
        throw new RuntimeException("Pet não existe");

    }

    @Override
    public String deletePet(Long id) {
        Pet pet = repository.findById(id).get();
        if(pet.getAble()){
            pet.setAble(false);
            repository.save(pet);
        }
        return "Pet deletado";
    }
}
