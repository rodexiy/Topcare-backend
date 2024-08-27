package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.service.implementation.ProductServiceImpl;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {
    private ProductServiceImpl service;

    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Product> register(@RequestPart ProductPostDTO product, @RequestPart List<MultipartFile> image) {
        System.out.println(image);
        return new ResponseEntity<>(service.register(product, image), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDTO> getProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getProduct(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(name = "/{id}",consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Product> updateProduct(@RequestPart ProductPutDTO productPutDTO, @RequestPart List<MultipartFile> images, @RequestParam Long id){
        try {
            return new ResponseEntity<>(service.putProduct(productPutDTO, images, id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("orderByRating")
    public ResponseEntity<List<Product>> getAllProductsByRating(){
        return new ResponseEntity<>(service.orderAllByRating(), HttpStatus.OK);
    }

    @GetMapping("orderBySale")
    public ResponseEntity<List<Product>> getAllBySale(){
        return new ResponseEntity<>(service.findAllProductBySale(), HttpStatus.OK);
    }
}