package net.weg.topcare.controller.dto.cartorder;

import net.weg.topcare.entity.Address;
import net.weg.topcare.entity.Client;
import net.weg.topcare.entity.ProductOrder;

import java.time.LocalDate;
import java.util.List;

public record CartOrderMaximalGetDTO(
        Client client,
        Address address,
        String orderStatus,
        Long numberProduct,
        LocalDate dateOrderFinished,
        List<ProductOrder> products
) {
}
