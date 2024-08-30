package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.card.CardGetRequestDTO;
import net.weg.topcare.controller.dto.card.CardPostRequestDTO;
import net.weg.topcare.exceptions.CardNotFoundException;
import net.weg.topcare.service.implementation.CardServiceImpl;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CardGetRequestDTO> addCard(@RequestBody CardPostRequestDTO dto) {
        return ResponseEntity.ok(service.addCard(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<CardGetRequestDTO>> getAllCard(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllCardByClientId(id));
    }

    @PatchMapping("{id}/{cardId}")
    public ResponseEntity<List<CardGetRequestDTO>> patchCardStandard(@PathVariable Long id, @PathVariable Long cardId) {
        try {
            return new ResponseEntity<>(service.patchCardStandard(id, cardId), HttpStatus.OK);
        } catch (CardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}/{cardId}")
    public ResponseEntity<Boolean> deleteCard(@PathVariable Long id, @PathVariable Long cardId) {
        return ResponseEntity.ok(service.deleteCard(id, cardId));
    }
}
