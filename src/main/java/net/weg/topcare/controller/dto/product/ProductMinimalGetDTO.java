package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.rating.GeneralRatingGetDTO;
import net.weg.topcare.entity.Image;

import java.util.List;

public record ProductMinimalGetDTO(
        Long id,
        String name,
        Double price,
        Integer discount,
        List<Image> images,
        GeneralRatingGetDTO generalRating
) {}
