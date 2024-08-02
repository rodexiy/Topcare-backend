package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;

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
        Product saved = repository.save(product);
        saved.setGeneralRating(5);
        return repository.save(saved);

    }

    @Override
    public List<Product> findAll(){
        return repository.findAll();
    }

    @Override
    public List<Product> findByIds(List<Long> ids){
        return repository.findAllById(ids);
    }

    @Override
    public Product update(ProductPutDTO dto) {
        Product product = repository.findById(dto.id()).get();
        BeanUtils.copyProperties(dto, product);
        return repository.save(product);
    }

    @Override

    public List<Product> orderAllByRating() {
        List<Product> products = repository.findAll();
        for (Product product : products){
            return repository.getTopByRatings(product.getRatings());
        }
        return null;
    }

    @Override
    public ProductGetDTO findOne(Long id) {
        Product product = repository.findById(id).get();
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
