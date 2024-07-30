package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
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
    public Pet patchPet(PetPatchRequestDTO dto) {
        if(repository.existsById(dto.idPet())){
            Pet pet = repository.findById(dto.idPet()).get();

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
        pet.setAble(false);
        repository.save(pet);
        return "Pet deletado";
    }

    public List<Pet> getAllPetsByClient(Long client_id){
        return repository.findPetsByClient_Id(client_id);
    }
}
