package net.weg.topcare.controller.dto.cartorder;

import net.weg.topcare.entity.CartOrder;

public record CartOrderGetAllDTO(
        Long clientId,
        Long cartOrderId
) {
}
