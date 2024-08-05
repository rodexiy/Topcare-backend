package net.weg.topcare.controller.dto.rating;

import net.weg.topcare.controller.dto.client.ClientGetIdDTO;
import net.weg.topcare.controller.dto.product.ProductGetIdDTO;

public record GeneralRatingPostDTO(
        Integer rating,
        Long amount,
        ProductGetIdDTO product,
        ClientGetIdDTO client
) {
}
