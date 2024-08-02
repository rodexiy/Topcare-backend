package net.weg.topcare.service.implementation;

import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import net.weg.topcare.entity.Brand;
import net.weg.topcare.entity.Product;
import net.weg.topcare.exceptions.BrandNotFoundException;
import net.weg.topcare.repository.BrandRepository;
import net.weg.topcare.service.interfaces.BrandServiceInt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandServiceInt {
    private final BrandRepository repository;
    @Override
    public Brand getBrand(Long id) throws BrandNotFoundException {
        return repository.findById(id).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public Brand addBrand(BrandPostDTO dto) {
        Brand brand = new Brand(dto);
        Product product = new Product(dto.product().id());
        List<Product> products = new ArrayList<>();
        products.add(product);
        brand.setProducts(products);
        return repository.save(brand);
    }
}
