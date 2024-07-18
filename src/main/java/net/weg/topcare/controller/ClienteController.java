package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cliente.ClienteGetDTO;
import net.weg.topcare.controller.dto.cliente.ClientePostDTO;
import net.weg.topcare.service.implementation.ClienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {
    private ClienteServiceImpl service;

    @PostMapping
    public ResponseEntity<ClienteGetDTO> cadastrar(@RequestBody ClientePostDTO cliente) {
        return ResponseEntity.ok(service.cadastrar(cliente));
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
