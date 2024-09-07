package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.entity.ProductCart;
import net.weg.topcare.service.implementation.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartServiceImpl service;
    @GetMapping("/products/{idClient}")
    public ResponseEntity<List<ProductCart>> getAllProductsInCart(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getAll(idClient), HttpStatus.OK);
    }
    @GetMapping("/priceDiscounted/{idClient}")
    public ResponseEntity<Double> getTotalPriceDiscounted(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getCartTotalPriceDiscounted(idClient), HttpStatus.OK);
    }
    @GetMapping("/priceNotDiscounted/{idClient}")
    public ResponseEntity<Double> getTotalPriceNotDiscounted(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getCartTotalPriceNotDiscounted(idClient), HttpStatus.OK);
    }
    @GetMapping("/totalDiscounted/{idClient}")
    public ResponseEntity<Double> gettotalDiscounted(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getCartTotalDiscount(idClient), HttpStatus.OK);
    }
}
