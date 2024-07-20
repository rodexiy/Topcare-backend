package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.ProductSpecification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductPostDTO(
        String name,
        String description,
        List<CategoryPostDTO> categories,
        List<ProductSpecificationPostDTO> specifications,
//        List<MultipartFile> images,
        Integer discount,
        Double price,
        Integer stock
) {}
