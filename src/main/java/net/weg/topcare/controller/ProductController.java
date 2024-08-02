package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
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

    @GetMapping("/search")
    public ResponseEntity<Page<ProductMinimalGetDTO>> search(@RequestParam String q, @RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(service.searchProduct(q, page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> register(@RequestBody ProductPostDTO dto) {
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDTO> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductPutDTO dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/rating")
    public ResponseEntity<List<Product>> getAllByRating() {
        return new ResponseEntity<>(service.orderAllByRating(), HttpStatus.OK);
    }
}