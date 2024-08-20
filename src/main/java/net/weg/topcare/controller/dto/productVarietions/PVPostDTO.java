package net.weg.topcare.controller.dto.productVarietions;


public record PVPostDTO(
        String name,
        Double price,
        Integer stock,
        Long productId
) {
}
