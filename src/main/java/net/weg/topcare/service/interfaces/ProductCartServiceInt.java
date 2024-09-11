package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.cart.ProductToCartDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ProductCartServiceInt {
    ProductToCartDTO addProductToCart(Long idProduct, Integer amount, Long idClient) throws ProductNotFoundException;
    Boolean removeProductFromCart(Long idProduct, Long idClient);
    Boolean updateProductAmount(Long idProduct, Integer amount, Long idClient);

    Boolean selectProduct(Long idProduct);
}
