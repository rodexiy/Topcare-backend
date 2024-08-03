package net.weg.topcare.service.interfaces;

import net.weg.topcare.entity.ProductOrder;

import java.util.List;

public interface ProductOrderServiceInt {
    List<ProductOrder> getAllByProductOrder();
    ProductOrder postProductOrder(ProductOrderPostDTO dto);
}
