package net.weg.topcare.controller.dto.product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationGetDTO;
import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.entity.Brand;
import net.weg.topcare.entity.Category;
import net.weg.topcare.entity.Image;

import java.util.List;

public record ProductGetDTO(
        Long id,
        Brand brand,
        String name,
        Double price,
        List<Category> categories,
        GeneralRatingGetDTO generalRating,
        @PositiveOrZero @NotNull
        Integer discount,
        String description,
        List<ProductSpecificationGetDTO> specifications,
        List<Image> images,
        Integer stock
){}
