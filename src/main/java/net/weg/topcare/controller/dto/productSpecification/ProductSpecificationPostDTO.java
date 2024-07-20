package net.weg.topcare.controller.dto.productSpecification;

import net.weg.topcare.entity.Product;

public record ProductSpecificationPostDTO(
        String name,
        String value,
        Product product
) {
}
