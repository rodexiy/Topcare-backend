package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.service.implementation.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetServiceImpl service; //todos os finals é required
    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody PetPostRequestDTO dto){
        return ResponseEntity.ok(service.postPet(dto));

    }
}
