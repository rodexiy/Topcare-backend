package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.exceptions.ProductSpecificationNotFound;
import net.weg.topcare.repository.ProductSpecificationRepository;
import net.weg.topcare.service.interfaces.ProductSpecificationServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSpecificationServiceImpl implements ProductSpecificationServiceInt {

    private final ProductSpecificationRepository repository;

    @Override
    public ProductSpecification addProductSpecification(ProductSpecificationPostDTO dto) {
        ProductSpecification specification = new ProductSpecification(dto);
        Product product = new Product(dto.product().id());
        specification.setProduct(product);
        return repository.save(specification);
    }

    @Override
    public ProductSpecification getProductSpecification(Long id) throws ProductSpecificationNotFound {
        return repository.findById(id).orElseThrow(ProductSpecificationNotFound::new);
    }

    @Override
    public List<ProductSpecification> getAllProductSpecifications() {
        return repository.findAll();
    }
}
