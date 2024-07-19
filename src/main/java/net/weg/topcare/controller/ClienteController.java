package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cliente.ClienteGetDTO;
import net.weg.topcare.controller.dto.cliente.ClientePostDTO;
import net.weg.topcare.service.implementation.ClienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {
    private ClienteServiceImpl service;

    @PostMapping
    public ResponseEntity<Long> cadastrar(@RequestBody ClientePostDTO cliente) {
        return ResponseEntity.ok(service.cadastrar(cliente));
    }

    @PostMapping("/existe")
    public ResponseEntity<Boolean> existe(@RequestBody String email) {
        System.out.println(email);
        return ResponseEntity.ok(service.existe(email));
    }

    @GetMapping
    public ResponseEntity<List<ClienteGetDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteGetDTO> buscarUm(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarUm(id));
    }


}
