package net.weg.topcare.controller.dto.product;
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
        Integer discount,
        String description,
        List<ProductSpecificationGetDTO> specifications,
        List<Image> images,
        Integer stock
){}
