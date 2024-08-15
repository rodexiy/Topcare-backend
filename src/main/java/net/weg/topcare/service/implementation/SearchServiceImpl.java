package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl {
    private final ProductRepository productRepository;

    public Page<ProductMinimalGetDTO> searchProduct(String query, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        System.out.println("Query: " + query);

        Page<Product> productPage = productRepository.findAllByNameContainingIgnoreCase(query, pageRequest);

        List<ProductMinimalGetDTO> dtos = productPage.stream()
                .map(Product::toMinimalGetDTO)
                .toList();

        return new PageImpl<>(dtos, pageRequest, productPage.getTotalElements());
    }
}
