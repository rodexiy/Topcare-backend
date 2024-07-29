package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.service.implementation.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private ProductServiceImpl service;

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody ProductPostDTO dto){
        return new ResponseEntity<>(service.postProduct(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDTO> getProduct(@PathVariable Long id){
        return new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductPutDTO dto){
        return new ResponseEntity<>(service.putProduct(dto), HttpStatus.OK);
    }

    @PatchMapping
}
