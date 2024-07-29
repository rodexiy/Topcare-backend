package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;

import java.util.List;

public interface ProductServiceInt {

    Product postProduct(ProductPostDTO dto);

    ProductGetDTO getProduct(Long id);
    List<Product> getAll();
    List<Product> findByIds(List<Long> ids);
    Product putProduct(ProductPutDTO dto);
}
