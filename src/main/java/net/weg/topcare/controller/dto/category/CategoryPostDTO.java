package net.weg.topcare.controller.dto.category;

import net.weg.topcare.entity.Product;

import java.util.List;

public record CategoryPostDTO(
        String name,
        List<Long> productsInCategory
) {
}
