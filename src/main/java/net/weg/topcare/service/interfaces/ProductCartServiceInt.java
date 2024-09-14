package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.cart.ProductCartGetDTO;
import net.weg.topcare.controller.dto.cart.ProductCartSelectDto;
import net.weg.topcare.controller.dto.cart.ProductToCartDTO;
import net.weg.topcare.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductCartServiceInt {
    ProductToCartDTO addProductToCart(Long idProduct, ProductCartGetDTO dto, Long idClient);
    Boolean removeProductFromCart(Long idProduct, Long idClient);
    Boolean updateProductAmount(Long idProduct, Integer amount, Long idClient);

    Boolean selectProduct(Long idProduct, ProductCartSelectDto dto);
}
