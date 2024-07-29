package net.weg.topcare.controller.dto.product;

import net.weg.topcare.entity.*;

import java.util.List;

public record ProductPutDTO(
        Long id,
        Brand brand,
        String name,
        String description,
        Integer generalRating,
        List<Category> categories,
        List<ProductSpecification> specifications,
        List<Image> images,
        Integer discount,
        Double price,
        Integer stock,
        List<Rating> ratings
) {
}
