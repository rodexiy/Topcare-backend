package net.weg.topcare.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.ClientPostDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.exceptions.CPFAlreadyBeingUsedException;
import net.weg.topcare.service.implementation.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Long> register(@Valid @RequestBody ClientPostDTO client) {
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


}
