package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;

import java.util.List;

public interface ProductServiceInt {

    Product register(ProductPostDTO dto);

    ProductGetDTO findOne(Long id);
    List<Product> findAll();
    List<Product> findByIds(List<Long> ids);
    Product update(ProductPutDTO dto);
    List<Product> orderAllByRating();
}
