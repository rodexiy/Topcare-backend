package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;

import net.weg.topcare.controller.dto.product.*;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Product;

import net.weg.topcare.entity.ProductOrder;
import net.weg.topcare.entity.ProductSpecification;
import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.repository.CategoryRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInt {

    private ProductRepository repository;
    private ProductOrderServiceImpl productOrderService;
    private CategoryRepository categoryRepository;

    @Override
    public Product register(ProductPostDTO dto) {
        Product product = new Product(dto);
        Product saved = repository.save(product);
        saved.setGeneralRating(5);
        List<ProductSpecification> specifications = new ArrayList<>();
        dto.specifications().forEach(specification -> {
            ProductSpecification productSpecification = new ProductSpecification(specification);
            productSpecification.setProduct(saved);
            specifications.add(productSpecification);
        });
        saved.setSpecifications(specifications);
        List<Category> categories = new ArrayList<>();
        dto.categories().forEach(category -> {
            Category category1 = new Category(category);
            category1.getProductsInCategory().add(saved);
            categories.add(category1);
            categoryRepository.save(category1);
        });
        saved.setCategories(categories);
        return repository.save(saved);

    }

    @Override
    public List<Product> findAllProductBySale() {
       List<ProductOrder> productOrders = productOrderService.getAllByProductOrder();
       List<Product> products = new ArrayList<>();
        ListIterator<ProductOrder> iterator = productOrders.listIterator();
        iterator.forEachRemaining(productOrder -> {
            products.add(productOrder.getProduct());
        });
       return products;
    }

    @Override
    public List<Product> findByIds(List<Long> ids){
        return repository.findAllById(ids);
    }

    @Override
    public Product putProduct(ProductPutDTO dto) throws ProductNotFoundException {
        Product product = repository.findById(dto.id()).orElseThrow(ProductNotFoundException::new);
        BeanUtils.copyProperties(dto, product);
        return repository.save(product);
    }

    @Override
    public List<Product> orderAllByRating() {
        return repository.findAllByOrderByRatingsDesc();
    }

    @Override
    public List<Product> getAllByBrandId(Long brand_id) {
        return repository.getProductsByBrand_Id(brand_id);
    }

    @Override
    public Product putProductRating(Long id, ProductPatchRatingDTO dto) throws ProductNotFoundException {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setGeneralRating(dto.rating());
        return repository.save(product);
    }

    @Override
    public ProductGetDTO getProduct(Long id) throws ProductNotFoundException {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        return product.toGetDTO();
    }

    public Page<ProductMinimalGetDTO> searchProduct(String query, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        System.out.println("Query: " + query);

        Page<Product> productPage = repository.findAllByNameContainingIgnoreCase(query, pageRequest);

        List<ProductMinimalGetDTO> dtos = productPage.stream()
                .map(Product::toMinimalGetDTO)
                .toList();

        return new PageImpl<>(dtos, pageRequest, productPage.getTotalElements());
    }
}
