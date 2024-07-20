package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.entity.ProductSpecification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductGetDTO(
        Long id,
        String name,
        Double price,
        GeneralRatingGetDTO generalRating,
        Integer discount,
        String description,
        List<ProductSpecification> specifications,
        List<String> images
){}
