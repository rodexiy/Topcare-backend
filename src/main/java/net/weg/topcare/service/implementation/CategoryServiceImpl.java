package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.category.CategoryPatchDTO;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.category.CategoryPutDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.CategoryRepository;
import net.weg.topcare.service.interfaces.CategoryServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServiceInt {

    private CategoryRepository repository;
    private ProductServiceImpl productService;

    @Override
    public Category postCategory(CategoryPostDTO dto) {
        Category category = new Category();
        category.setName(dto.name());
        List<Product> products = productService.findByIds(dto.productsInCategory());
        category.setProductsInCategory(products);
        for (Product product : products){
            product.getCategories().add(category);
        }
        return repository.save(category);
    }

    @Override
    public Category putCategory(CategoryPutDTO dto) {
        Category category = repository.findById(dto.id()).get();
        List<Product> products = productService.findByIds(dto.productsInCategory());
        category.setProductsInCategory(products);
        return repository.save(category);
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = repository.findById(id).get();
        repository.delete(category);
        return "Categoria deletada!";
    }

    @Override
    public Category patchCategory(CategoryPatchDTO dto, Long id) {
        Category category = repository.findById(id).get();
        category.setName(dto.name());
        return repository.save(category);
    }

    @Override
    public CategoryGetDTO getCategory(Long id) {
        Category category = repository.findById(id).get();
        return category.toGetDTO();
    }
}
