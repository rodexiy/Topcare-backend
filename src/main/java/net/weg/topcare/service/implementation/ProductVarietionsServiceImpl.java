package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.controller.dto.productVarietions.PVPostDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.entity.ProductVarietions;
import net.weg.topcare.exceptions.ProductSpecificationNotFound;
import net.weg.topcare.repository.ProductVarietionsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVarietionsServiceImpl {

    private final ProductVarietionsRepository repository;

    public ProductVarietions addProductVarietions(PVPostDTO dto) {
        ProductVarietions varietions = new ProductVarietions(dto);
        Product product = new Product(dto.productId());
        varietions.setProduct(product);
        return repository.save(varietions);
    }

    public ProductVarietions getProductVarietions(Long id) throws ProductSpecificationNotFound {
        return repository.findById(id).orElseThrow(ProductSpecificationNotFound::new);
    }

}
