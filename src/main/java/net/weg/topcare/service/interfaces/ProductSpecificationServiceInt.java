package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.exceptions.ProductSpecificationNotFound;

import java.util.List;

public interface ProductSpecificationServiceInt {
    ProductSpecification addProductSpecification(ProductSpecificationPostDTO dto);
    ProductSpecification getProductSpecification(Long id) throws ProductSpecificationNotFound;
    List<ProductSpecification> getAllProductSpecifications();
}
