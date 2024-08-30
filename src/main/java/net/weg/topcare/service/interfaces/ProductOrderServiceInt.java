package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.productOrder.ProductOrderPostDTO;
import net.weg.topcare.entity.ProductOrder;

import java.util.List;

public interface ProductOrderServiceInt {
    List<ProductOrder> getAllByProductOrder();
    ProductOrder postProductOrder(ProductOrderPostDTO dto);
    List<ProductOrder> getProductOrderByProducts(Long productId);
}
