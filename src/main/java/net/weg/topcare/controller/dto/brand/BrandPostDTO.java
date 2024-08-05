package net.weg.topcare.controller.dto.brand;

import net.weg.topcare.controller.dto.product.ProductGetIdDTO;
import net.weg.topcare.controller.dto.product.ProductPostDTO;

public record BrandPostDTO(
        String name,
        ProductGetIdDTO product
) {
}
