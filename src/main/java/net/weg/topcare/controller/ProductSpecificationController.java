package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.service.implementation.ProductSpecificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/specification")
public class ProductSpecificationController {

    private final ProductSpecificationServiceImpl service;
    @PostMapping
    public ResponseEntity<ProductSpecification> postSpecification(@RequestBody ProductSpecificationPostDTO dto){
        return new ResponseEntity<>(service.addProductSpecification(dto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductSpecification> getSpecification(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.getProductSpecification(id), HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductSpecification>> getAllSpecifications(){
        return new ResponseEntity<>(service.getAllProductSpecifications(), HttpStatus.FOUND);
    }
}
