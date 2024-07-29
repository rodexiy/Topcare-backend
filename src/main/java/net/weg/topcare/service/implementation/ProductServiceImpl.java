package net.weg.topcare.service.implementation;

import lombok.AllArgsConstructor;
import net.weg.topcare.controller.dto.product.ProductGetDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;
import net.weg.topcare.controller.dto.product.ProductPutDTO;
import net.weg.topcare.entity.Product;
import net.weg.topcare.repository.ProductRepository;
import net.weg.topcare.service.interfaces.ProductServiceInt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
    public ProductGetDTO getProduct(Long id) {
        Product product = repository.findById(id).get();
       return product.toGetDTO();
    }
}
