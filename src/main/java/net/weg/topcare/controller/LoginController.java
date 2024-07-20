package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.controller.dto.client.LoginDTO;
import net.weg.topcare.service.interfaces.LoginServiceInt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {
    private LoginServiceInt service;

    @PostMapping
    public ResponseEntity<Long> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(service.login(loginDTO));
    }

    @GetMapping("/context/{id}")
    public ResponseEntity<String> loginContext(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLoginContext(id));
    }


}
