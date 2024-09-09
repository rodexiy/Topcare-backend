package net.weg.topcare.service.implementation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import net.weg.topcare.entity.Brand;
import net.weg.topcare.entity.Image;
import net.weg.topcare.entity.Product;
import net.weg.topcare.exceptions.BrandNotFoundException;
import net.weg.topcare.repository.BrandRepository;
import net.weg.topcare.repository.ImageRepository;
import net.weg.topcare.service.interfaces.BrandServiceInt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandServiceInt {
    private final BrandRepository repository;
    private final ProductServiceImpl productService;
    private final ImageRepository imageRepository;
    @Override
    public Brand getBrand(Long id) throws BrandNotFoundException {
        return repository.findById(id).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public Brand addBrand(BrandPostDTO dto, MultipartFile image) {
        Brand brand = new Brand(dto);
        List<Product> products = productService.getAllByBrandId(brand.getId());
        brand.setProducts(products);
        try {
            Image image1 = new Image(image);
            imageRepository.save(image1);
            brand.setImage(image1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Product product : products) {
            product.setBrand(brand);
        }
        return repository.save(brand);
    }

    @Override
    public List<Brand> getTopRatedBrands() {
        List<Brand> topRatedBrands = new ArrayList<>();
        for (Product product : productService.orderAllByRating()){
            topRatedBrands.add(product.getBrand());
        }
        return topRatedBrands;
    }
}
