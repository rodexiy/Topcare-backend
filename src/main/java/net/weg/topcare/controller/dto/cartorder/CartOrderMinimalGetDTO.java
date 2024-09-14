package net.weg.topcare.controller.dto.cartorder;

import net.weg.topcare.enums.PaymentMethod;

import java.time.LocalDate;

public record CartOrderMinimalGetDTO(
        Long id,
        String name,
        String state,
        String payment,
        String date
) {
}
