package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
<<<<<<< HEAD
=======
import net.weg.topcare.entity.Rating;
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======

import java.util.ArrayList;
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductServiceInt {

    private ProductRepository repository;

    @Override
    public Product postProduct(ProductPostDTO dto) {
        Product product = new Product(dto);
        return repository.save(product);
    }

    @Override
    public List<Product> getAll(){
        return repository.findAll();
    }

    @Override
    public List<Product> findByIds(List<Long> ids){
        return repository.findAllById(ids);
    }

    @Override
    public Product putProduct(ProductPutDTO dto) {
        Product product = repository.findById(dto.id()).get();
        BeanUtils.copyProperties(dto, product);
        return repository.save(product);
    }

    @Override
<<<<<<< HEAD
=======
    public List<Product> orderAllByRating() {
        List<Product> products = repository.findAll();
        for (Product product : products){
            return repository.getTopByRatings(product.getRatings());
        }
        return null;
    }

    @Override
>>>>>>> 00364167a70c64871018c2e96f7d45ee524bbf9d
    public ProductGetDTO getProduct(Long id) {
        Product product = repository.findById(id).get();
        return product.toGetDTO();
    }

    public Page<ProductMinimalGetDTO> searchProduct(String query, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // Buscar os produtos com base no nome contendo o termo da query (ignorando case)
        List<Product> products = repository.findAllByNameContainingIgnoreCase(query, pageRequest);

        // Mapear os produtos para DTOs mínimos e criar uma página paginada
        List<ProductMinimalGetDTO> dtos = products.stream()
                .map(Product::toMinimalGetDTO)
                .toList(); // ou .collect(Collectors.toList()) se não estiver usando Java 16+

        return new PageImpl<>(dtos, pageRequest, products.size());
    }
}
