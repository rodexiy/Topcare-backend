package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.CategoryRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.CategoryServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInt {

    private CategoryRepository repository;
    private ProductRepository productRepository;

    @Override
    public Category postCategory(CategoryPostDTO dto) {
        Category category = new Category(dto);
        return repository.save(category);
    }
}
