package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.service.implementation.ImageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/image")
@CrossOrigin("*")
public class ImageController {

    private final ImageServiceImpl service;

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Boolean> deleteImageById(@PathVariable Long imageId) {
        return new ResponseEntity<>(service.deleteImageById(imageId), HttpStatus.OK);
    }
}
