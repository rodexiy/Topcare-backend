package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Image;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.repository.PetRepository;
import net.weg.topcare.service.interfaces.PetServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetServiceInt {
    private final PetRepository repository;
    private final ImageRepository imageRepository;
    @Override
    public PetGetRequestDTO postPet(PetPostRequestDTO dto, MultipartFile picture) {
        System.out.printf("dto: " + dto);
        Pet pet = new Pet(dto);
        Client client = new Client();
        client.setId(dto.idClient());
        pet.setClient(client);
        System.out.println("Pet: " + pet);
        try {
            Image img = new Image(picture);
            imageRepository.save(img);
            pet.setPicture(img);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            repository.save(pet);

        }
        return pet.toDto();
    }

    @Override
    public PetGetRequestDTO getOnePet(Long id) {
        Optional<Pet> pet = repository.findById(id);
        if(pet.get().getAble()){
            return pet.map(Pet::toDto).get();

        }
        throw new RuntimeException("Pet não existe");
    }

    @Override
    public List<PetGetRequestDTO> getPets() {
        List<Pet> pet = repository.findAll();
        return pet.stream().filter(pet1 -> pet1.getAble() == true).map(Pet::toDto).toList();
    }

    @Override
    public Pet patchPet(PetPatchRequestDTO dto, Long id, MultipartFile file) {
        System.out.println(file);
        if(repository.existsById(id)){
            Pet pet = repository.findById(id).get();

            if(pet.getAble()){
                pet.setName(dto.name());
                pet.setSize(dto.size());
                pet.setBirthdate(dto.birthdate());
                pet.setWeight(dto.weight());
                try {
                    Image img = new Image(file);
                    imageRepository.save(img);
                    pet.setPicture(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public List<Pet> getAllPetsByClient(Long client_id){
        return repository.findPetsByClient_Id(client_id);
    }
}
