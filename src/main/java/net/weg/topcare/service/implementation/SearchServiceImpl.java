package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductFilter;
import net.weg.topcare.repository.CategoryRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.SearchServiceInt;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchServiceInt {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private List<Category> getCategoriesFromQuery(String query, ProductFilter productFilter) {
        List<Category> categories = new ArrayList<>();

        String[] splitted = query.split(" ");
        for (String split : splitted) {

            if (productFilter.getCategories().isEmpty()){
                categories.addAll(categoryRepository.findAllByNameContainingIgnoreCase(split));
            }else {
                List<Category> categories1 = categoryRepository.findAllByNameIn(productFilter.getCategories());
                categories.addAll(categories1);


            }
        }

        return categories;
    }

    private Integer getNumberOfCategories(String query) {
        String[] splitted = query.split(" ");
        return splitted.length + 1;
    }

    private PageRequest createPageRequest(int page, int size, String sorter) {
        Sort sort = Sort.by(
                Sort.Order.asc("price"),
                Sort.Order.desc("discount"));

        return switch (sorter) {
            case "higherPrice" -> PageRequest.of(page, size, sort.descending());
            case "lowerPrice" -> PageRequest.of(page, size, sort.ascending());
            default -> PageRequest.of(page, size, Sort.Direction.DESC, "generalRating");
        };
    }

    @Override
    public Page<ProductMinimalGetDTO> searchProduct(String query, int page, int size, String sorter, ProductFilter productFilter) {
        PageRequest pageRequest = createPageRequest(page, size, sorter);

        List<Category> categories = getCategoriesFromQuery(query, productFilter);
        int numberOfCategories = getNumberOfCategories(query);

        Page<Product> productPage;
        if (productFilter.getCategories().isEmpty()) {
            productPage = productRepository.findProductsByCategoriesOrNameLike(query, categories, numberOfCategories, pageRequest);
        }else {
            productPage = productRepository.findProductsByCategories(categories, numberOfCategories, pageRequest);
        }

        List<ProductMinimalGetDTO> dtos = productPage.stream()
                .map(Product::toMinimalGetDTO)
                .toList();

        return new PageImpl<>(dtos, pageRequest, productPage.getTotalElements());
    }


}
