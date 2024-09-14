package net.weg.topcare.controller;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cart.ProductCartGetDTO;
import net.weg.topcare.controller.dto.cart.ProductCartSelectDto;
import net.weg.topcare.controller.dto.cart.ProductToCartDTO;
import net.weg.topcare.service.implementation.ProductCartServiceImpl;
import net.weg.topcare.service.implementation.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productCart")
@CrossOrigin("*")
public class ProductCartController {
    private final ProductCartServiceImpl service;
    @PostMapping("/{idClient}/{idProduct}")
    public ResponseEntity<ProductToCartDTO> addProductToCart(@PathVariable Long idClient, @PathVariable Long idProduct, @RequestBody ProductCartGetDTO dto){
        return new ResponseEntity<>(service.addProductToCart(idProduct, dto, idClient), HttpStatus.OK);
    }
    @DeleteMapping("/{idClient}/{idProduct}")
    public ResponseEntity<Boolean> removeProductFromCart(@PathVariable Long idClient, @PathVariable Long idProduct){
        return new ResponseEntity<>(service.removeProductFromCart(idProduct, idClient), HttpStatus.OK);
    }
    @PatchMapping("/amount/{idClient}/{idProduct}")
    public ResponseEntity<Boolean> updateProductAmount(@PathVariable Long idClient, @PathVariable Long idProduct, @RequestBody ProductCartGetDTO dto){
        return new ResponseEntity<>(service.updateProductAmount(idProduct, dto.amount(), idClient), HttpStatus.OK);
    }
    @PatchMapping("/select/{idProduct}")
    public ResponseEntity<Boolean> selectProduct(@PathVariable Long idProduct, @RequestBody ProductCartSelectDto dto){
        return new ResponseEntity<>(service.selectProduct(idProduct, dto), HttpStatus.OK);
    }
}
