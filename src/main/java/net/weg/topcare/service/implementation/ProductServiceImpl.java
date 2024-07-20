package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInt {

    private ProductRepository repository;
    private CategoryServiceImpl categoryService;

    @Override
    public Product postProduct(ProductPostDTO dto) {
        Product product = new Product(dto);
        CategoryPostDTO categoryPostDTO = dto.categories().stream().map(dto1 -> new CategoryPostDTO(dto1.name(), List.of(product))).findAny().get();
        categoryService.postCategory(categoryPostDTO);
        return repository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).get();
    }
}
