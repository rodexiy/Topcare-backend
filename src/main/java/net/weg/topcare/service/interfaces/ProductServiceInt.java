package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.product.*;
import net.weg.topcare.entity.Product;
import net.weg.topcare.entity.ProductOrder;
import net.weg.topcare.entity.Rating;
import net.weg.topcare.exceptions.ProductNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceInt {

    Product register(ProductPostDTO dto, MultipartFile image);
    List<Product> findAllProductBySale();
    ProductGetDTO getProduct(Long id) throws ProductNotFoundException;
    Product putProduct(ProductPutDTO dto) throws ProductNotFoundException;
    List<Product> findByIds(List<Long> ids);
    List<Product> orderAllByRating();
    List<Product> getAllByBrandId(Long brand_id);
    Product putProductRating(Long id, ProductPatchRatingDTO dto) throws ProductNotFoundException;
}
