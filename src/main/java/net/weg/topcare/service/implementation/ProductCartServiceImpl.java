package net.weg.topcare.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.cart.ProductCartGetDTO;
import net.weg.topcare.controller.dto.cart.ProductCartSelectDto;
import net.weg.topcare.controller.dto.cart.ProductToCartDTO;
import net.weg.topcare.entity.Cart;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductCart;
import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.repository.CartRepository;
import net.weg.topcare.repository.ClientRepository;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.repository.ProductCartRepository;
import net.weg.topcare.service.interfaces.ProductCartServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCartServiceImpl implements ProductCartServiceInt {
    private final ProductCartRepository productCartRepository;
    private final ProductServiceImpl productService;
    private final ClientServiceImpl clientService;
    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final ImageRepository imageRepository;

    @Override
    public ProductToCartDTO addProductToCart(Long idProduct, ProductCartGetDTO dto, Long idClient) throws ProductNotFoundException {
        Product product = productService.getProductById(idProduct);
        Client client = clientService.findOneClient(idClient);
        ProductCart productCart = new ProductCart();
        productCart.setProduct(product);
        productCart.setImage(product.getImages().get(0));
        Cart cart = client.getCart();
        cart.getProductsInCart().add(productCart);
        productCart.setCart(cart);
        productCart.setAmount(dto.amount());
        productCart.setSelected(dto.select());

        ProductToCartDTO productToCartDTO = new ProductToCartDTO(
                product.getId(),
                dto.amount(),
                product.getImages().get(0),
                product.getName(),
                product.getPrice(),
                product.getDiscount(),
                productCart.getSelected());
        productCartRepository.save(productCart);
        return productToCartDTO;
    }

    @Override
    public Boolean removeProductFromCart(Long idProduct, Long idClient) {
        Client client = clientService.findOneClient(idClient);
        Cart cart = cartRepository.findById(client.getCart().getId()).get();
        List<ProductCart> productCarts = cart.getProductsInCart();
        for(ProductCart productCart : productCarts){
            if(productCart.getId().equals(idProduct)){
                productCarts.remove(productCart);
                productCartRepository.deleteById(idProduct);
                cart.setProductsInCart(productCarts);
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateProductAmount(Long idProduct, Integer amount, Long idClient) {
        Client client = clientService.findOneClient(idClient);
        for(ProductCart productCart : client.getCart().getProductsInCart()){
            if(productCart.getId().equals(idProduct)){
                productCart.setAmount(amount);
                productCartRepository.save(productCart);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean selectProduct(Long idProduct, ProductCartSelectDto dto) {
        ProductCart productCart = productCartRepository.findById(idProduct).get();
        if(productCart != null){
            productCart.setSelected(dto.select());
            productCartRepository.save(productCart);
            return productCart.getSelected();
        }
        return null;
    }

}
