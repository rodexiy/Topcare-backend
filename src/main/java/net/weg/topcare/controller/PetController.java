package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.service.implementation.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetServiceImpl service; //todios os fnals é required
    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody PetPostRequestDTO dto){
        return ResponseEntity.ok(service.postPet(dto));

    }
    @GetMapping("{id}")
    public ResponseEntity<PetGetRequestDTO> getOnePet(@PathVariable Long id){
        return ResponseEntity.ok(service.getOnePet(id));

    }
    @GetMapping
    public ResponseEntity<List<PetGetRequestDTO>> getPets(){
        return ResponseEntity.ok(service.getPets());
    }
}
