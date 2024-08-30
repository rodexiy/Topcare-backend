package net.weg.topcare.controller.dto.cartorder;

import java.util.Date;

public record OrderCartPostDTO(
        Long clientId,
        Long addressId,
        Long discount,
        Long quantity,
        Double freight,
        Long numberOrder,
        Date dateOrderFinished
) {
}
