package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.service.implementation.ProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {
    private ProductServiceImpl service;

    @PostMapping
    public ResponseEntity<Product> register(@RequestBody ProductPostDTO dto) {
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDTO> getProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody ProductPutDTO dto){
        try {
            return new ResponseEntity<>(service.putProduct(dto), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("orderByRating")
    public ResponseEntity<List<Product>> getAllProductsByRating(){
        return new ResponseEntity<>(service.orderAllByRating(), HttpStatus.OK);
    }
}