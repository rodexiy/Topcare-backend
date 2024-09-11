package net.weg.topcare.controller.dto.cart;

import net.weg.topcare.entity.Image;

public record ProductToCartDTO(
        Long id,
        Integer amount,
        Image image,
        String name,
        Double price,
        Integer discount,
        Boolean selected
) {}
