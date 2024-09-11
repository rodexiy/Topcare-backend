package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cart.ProductToCartDTO;
import net.weg.topcare.entity.ProductCart;
import net.weg.topcare.service.implementation.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {
    private final CartServiceImpl service;
    @GetMapping("/products/{idClient}")
    public ResponseEntity<List<ProductToCartDTO>> getAllProductsInCart(@PathVariable Long idClient){
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
    public ResponseEntity<Double> getTotalDiscounted(@PathVariable Long idClient){
        return new ResponseEntity<>(service.getCartTotalDiscount(idClient), HttpStatus.OK);
    }
}
