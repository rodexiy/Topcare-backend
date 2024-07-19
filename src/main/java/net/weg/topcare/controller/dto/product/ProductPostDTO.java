package net.weg.topcare.controller.dto.product;

import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.ProductSpecification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductPostDTO(
        String name,
        String description,
        List<Category> categories,
        List<ProductSpecification> specifications,
        List<MultipartFile> images,
        Integer discount,
        Double price,
        Integer stock
) {}
