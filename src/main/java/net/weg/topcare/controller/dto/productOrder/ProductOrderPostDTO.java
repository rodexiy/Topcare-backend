package net.weg.topcare.controller.dto.productOrder;

import net.weg.topcare.controller.dto.cartorder.CartOrderGetAllDTO;
import net.weg.topcare.controller.dto.product.ProductGetIdDTO;

public record ProductOrderPostDTO(
        String name,
        Double unitPrice,
        ProductGetIdDTO product,
        CartOrderGetAllDTO cartOrder
) {
}
