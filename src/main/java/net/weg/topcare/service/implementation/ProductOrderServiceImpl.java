package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productOrder.ProductOrderPostDTO;
import net.weg.topcare.entity.CartOrder;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductOrder;
import net.weg.topcare.repository.ProductOrderRepository;
import net.weg.topcare.service.interfaces.ProductOrderServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderServiceInt {

    private final ProductOrderRepository repository;

    @Override
    public List<ProductOrder> getAllByProductOrder() {
        return repository.findAllByOrderByProduct();
    }

    @Override
    public ProductOrder postProductOrder(ProductOrderPostDTO dto) {
        ProductOrder productOrder = new ProductOrder(dto);
        Product product = new Product(dto.product().id());
        CartOrder cartOrder = new CartOrder(dto.cartOrder().cartOrderId());
        productOrder.setCartOrder(cartOrder);
        productOrder.setProduct(product);
        return repository.save(productOrder);
    }

    @Override
    public List<ProductOrder> getProductOrderByProducts(Long productId) {
        return repository.getTopByProduct_IdOrderByProductDesc(productId);
    }
}
