package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;

import net.weg.topcare.controller.dto.product.*;
import net.weg.topcare.entity.*;

import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.repository.CategoryRepository;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInt {

    private ProductRepository repository;
    private ProductOrderServiceImpl productOrderService;
    private CategoryRepository categoryRepository;
    private ImageRepository imageRepository;

    @Override
    public Product register(ProductPostDTO dto, List<MultipartFile> images) {
        Product product = new Product(dto);
        Product saved = repository.save(product);
        saved.setGeneralRating(5);
        saved.setBrand(new Brand(dto.brand().id()));
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
        List<Image> imageArrayList = new ArrayList<>();
        constructImage(images, saved, imageArrayList);
        saved.setCategories(categories);
        saved.setImages(imageArrayList);
        return repository.save(saved);
    }

    @Override
    public List<ProductMinimalGetDTO> findAllProductBySale() {
       List<ProductOrder> productOrders = productOrderService.getAllByProductOrder();
       List<ProductMinimalGetDTO> products = new ArrayList<>();
        ListIterator<ProductOrder> iterator = productOrders.listIterator();
        iterator.forEachRemaining(productOrder -> {
            products.add(productOrder.getProduct().toMinimalGetDTO());
        });
       return products;
    }

    @Override
    public List<Product> findByIds(List<Long> ids){
        return repository.findAllById(ids);
    }

    @Override
    public Boolean deleteProduct(Long id) throws ProductNotFoundException {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        repository.delete(product);
        return true;
    }

    @Override
    public Product putProduct(ProductPutDTO dto, List<MultipartFile> images, Long id) throws ProductNotFoundException {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);
        BeanUtils.copyProperties(dto, product);
        List<Image> imagesList = imageRepository.getAllByProduct_Id(product.getId());
        constructImage(images, product, imagesList);
        List<Category> categories = product.getCategories();
        List<ProductSpecification> specifications = product.getSpecifications();
        for (Category category : categories){
            dto.categories().forEach(categoryDTO -> {
                if (categoryDTO.name().equals(category.getName())) {
                    return;
                } else {
                    Category category1 = new Category(categoryDTO);
                    category1.getProductsInCategory().add(product);
                    categoryRepository.save(category1);
                    categories.add(category1);
                }
            });
        }
        dto.specifications().forEach(specification -> {
            ProductSpecification productSpecification = new ProductSpecification(specification);
            productSpecification.setProduct(product);
            specifications.add(productSpecification);
        });
        product.setCategories(categories);
        product.setSpecifications(specifications);
        product.setImages(imagesList);
        product.setGeneralRating(5);
        return repository.save(product);
    }

    private void constructImage(List<MultipartFile> images, Product product, List<Image> imagesList) {
        for (MultipartFile file : images){
            try {
                Image image = new Image(file);
                image.setProduct(product);
                imagesList.add(image);
                imageRepository.save(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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


}
