package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.*;
import net.weg.topcare.entity.Client;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.exceptions.CPFAlreadyBeingUsedException;
import net.weg.topcare.service.implementation.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {
    private ClientServiceImpl service;

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody ClientPostDTO client) {
        try {
            return ResponseEntity.ok(service.register(client));

        }catch (CPFAlreadyBeingUsedException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestBody String email) {
        System.out.println(email);
        return ResponseEntity.ok(service.exists(email));
    }

    @GetMapping
    public ResponseEntity<List<ClientGetDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientGetDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> putClient(@RequestBody ClientPutDTO clientPutDTO, @PathVariable Long id){
        return ResponseEntity.ok(service.putClient(clientPutDTO, id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> patchClient(@RequestBody ClientPatchDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.changePassword(dto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteAccount(id));
    }
    @PostMapping("/checkEmail/{id}")
    public ResponseEntity<Integer> checkEmailAndCreateToken(@RequestBody ClientEmailDTO email, @PathVariable Long id){
        return ResponseEntity.ok(service.checkEmailAndCreateToken(email, id));
    }
    @PostMapping("/checkToken")
    public ResponseEntity<Boolean> checkToken(@RequestBody ClientTokenDTO dto){
        return ResponseEntity.ok(service.checkToken(dto));
    }
    @PatchMapping("/changePassword/{id}")
    public ResponseEntity<Boolean> changePassword(@RequestBody ClientPatchDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.changePassword(dto, id));
    }

}
