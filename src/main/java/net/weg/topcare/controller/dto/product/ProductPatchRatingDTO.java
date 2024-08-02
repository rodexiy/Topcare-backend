package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.rating.GeneralRatingPostDTO;
import net.weg.topcare.entity.Rating;

import java.util.List;

public record ProductPatchRatingDTO(
        Integer rating
) {
}
