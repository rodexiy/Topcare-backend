package net.weg.topcare.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.weg.topcare.controller.dto.pet.PetGetRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPatchRequestDTO;
import net.weg.topcare.controller.dto.pet.PetPostRequestDTO;
import net.weg.topcare.entity.Pet;
import net.weg.topcare.service.implementation.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PetController {
    private final PetServiceImpl service; //todios os fnals é required
    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<PetGetRequestDTO> postPet(@RequestPart PetPostRequestDTO dto, @RequestPart(required = false) MultipartFile picture) throws IOException {
        return ResponseEntity.ok(service.postPet(dto, picture));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Pet>> getPetsByClient(@PathVariable Long id){
        return new ResponseEntity<>(service.getAllPetsByClient(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PetGetRequestDTO>> getPets(){
        return ResponseEntity.ok(service.getPets());
    }

    @PatchMapping(value = "{id}", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Pet> patchPet(@RequestPart PetPatchRequestDTO dto, @PathVariable Long id, @RequestPart(required = false) MultipartFile file){
        return ResponseEntity.ok(service.patchPet(dto, id, file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id){
        return ResponseEntity.ok(service.deletePet(id));
    }


}
