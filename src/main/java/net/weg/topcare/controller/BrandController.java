package net.weg.topcare.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import net.weg.topcare.entity.Brand;
import net.weg.topcare.exceptions.BrandNotFoundException;
import net.weg.topcare.service.implementation.BrandServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brand")
@CrossOrigin("*")
public class BrandController {
    private final BrandServiceImpl service;
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return new ResponseEntity<>(service.getAllBrands(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Brand> postBrand(@RequestPart BrandPostDTO dto, @RequestPart MultipartFile image) {
        return new ResponseEntity<>(service.addBrand(dto, image), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getBrand(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/topRated")
    public ResponseEntity<List<Brand>> getTopRatedBrands(){
        return new ResponseEntity<>(service.getTopRatedBrands(), HttpStatus.OK);
    }
}
