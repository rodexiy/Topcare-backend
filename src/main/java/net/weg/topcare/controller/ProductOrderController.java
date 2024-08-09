package net.weg.topcare.controller;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productOrder.ProductOrderPostDTO;
import net.weg.topcare.entity.ProductOrder;
import net.weg.topcare.service.implementation.ProductOrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productorder")
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderServiceImpl service;

    @PostMapping
    public ResponseEntity<ProductOrder> postProductOrder(@RequestBody ProductOrderPostDTO dto){
        return new ResponseEntity<>(service.postProductOrder(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductOrder>> getAllByProductOrder(){
        return new ResponseEntity<>(service.getAllByProductOrder(), HttpStatus.OK);
    }
}
