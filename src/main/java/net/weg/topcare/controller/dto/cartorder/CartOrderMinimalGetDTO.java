package net.weg.topcare.controller.dto.cartorder;

import java.time.LocalDate;

public record CartOrderMinimalGetDTO(
        String name,
        String orderStatus,
        Long numberProduct,
        LocalDate dateOrderFinished
) {
}
