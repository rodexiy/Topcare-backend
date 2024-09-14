package net.weg.topcare.controller.dto.cartorder;

import net.weg.topcare.controller.dto.client.ClientGetDTO;
import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.ProductOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CartOrderMaximalGetDTO(
        Long id,
        Double freight,
        Double discount,
        String payment,
        Address address,
        String orderState,
        Long numberProduct,
        String date,
        List<ProductOrder> products,
        List<String> statuses
) {
}
