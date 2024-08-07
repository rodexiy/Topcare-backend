package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;

import net.weg.topcare.controller.dto.product.*;
import net.weg.topcare.entity.Product;

import net.weg.topcare.exceptions.ProductNotFoundException;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInt {

    private ProductRepository repository;

    @Override
    public Product register(ProductPostDTO dto) {
        Product product = new Product(dto);
        product.setGeneralRating(5);
        return repository.save(product);
//        Product product = new Product(dto);
//        product.setGeneralRating(5);
//        repository.save(product);
//
//        Product productCloned = product.clone();
//        productCloned.setGeneralRating(5);
//
//        return repository.save(productCloned);


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
