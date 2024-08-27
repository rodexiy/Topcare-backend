package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationGetDTO;
import net.weg.topcare.entity.*;

import java.util.List;

public record ProductPutDTO(
        Brand brand,
        String name,
        String description,
        Integer generalRating,
        List<CategoryGetDTO> categories,
        List<ProductSpecificationGetDTO> specifications,
        Integer discount,
        Double price,
        Integer stock,
        List<Rating> ratings
) {
}
