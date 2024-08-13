package net.weg.topcare.controller.dto.productVarietions;

import jdk.jshell.Snippet;
import net.weg.topcare.controller.dto.product.ProductGetIdDTO;
import net.weg.topcare.entity.Product;

public record PVPostDTO(
        String name,
        Double price,
        Integer stock,
        Long productId
) {
}
