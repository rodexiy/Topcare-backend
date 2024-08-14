package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import net.weg.topcare.service.implementation.CardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CardController {
    private final CardServiceImpl service;
    @PostMapping
    public ResponseEntity<CardPostRequestDTO> addCard(CardPostRequestDTO dto){
        return ResponseEntity.ok(service.addCard(dto));
    }
    @GetMapping("{id}")
    public ResponseEntity<List<CardGetRequestDTO>> getAllCard(Long id){
        return ResponseEntity.ok(service.getAllCard(id));
    }
    @PatchMapping("{id}")
    public ResponseEntity<Boolean> patchCardStandard(Long id, Long cardId){
        return ResponseEntity.ok(service.patchCardStandard(id, cardId));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteCard(Long id, Long cardId){
        return ResponseEntity.ok(service.deleteCard(id, cardId));
    }
}
