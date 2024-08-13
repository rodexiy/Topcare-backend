package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.controller.dto.productVarietions.PVPostDTO;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.entity.ProductVarietions;
import net.weg.topcare.service.implementation.ProductSpecificationServiceImpl;
import net.weg.topcare.service.implementation.ProductVarietionsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/productvarietions")
public class ProductVarietionController {

    private final ProductVarietionsServiceImpl service;

    @PostMapping
    public ResponseEntity<ProductVarietions> postVarietions(@RequestBody PVPostDTO dto){
        return new ResponseEntity<>(service.addProductVarietions(dto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductVarietions> getVarietions(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.getProductVarietions(id), HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
