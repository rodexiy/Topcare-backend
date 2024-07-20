package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.entity.Product;

public interface ProductServiceInt {

    Product postProduct(ProductPostDTO dto);

    Product getProduct(Long id);
}
