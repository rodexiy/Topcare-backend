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
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {
    private ProductServiceImpl service;

    @GetMapping("/search")
    public ResponseEntity<Page<ProductMinimalGetDTO>> searchProducts(@RequestParam String q, @RequestParam int page){
        int size = 13;
        return new ResponseEntity<>(service.searchProduct(q, page, size), HttpStatus.OK);
    }

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
<<<<<<< HEAD
=======

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductsByRating(){
        return new ResponseEntity<>(service.orderAllByRating(), HttpStatus.OK);
    }
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
}
