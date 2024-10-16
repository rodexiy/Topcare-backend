package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.brand.BrandPostDTO;
import net.weg.topcare.entity.Brand;
import net.weg.topcare.exceptions.BrandNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BrandServiceInt {
    Brand getBrand(Long id) throws BrandNotFoundException;
    List<Brand> getAllBrands();
    Brand addBrand(BrandPostDTO dto, MultipartFile image);
    List<Brand> getTopRatedBrands();
}
