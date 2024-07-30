package net.weg.topcare.controller.dto.category;

import java.util.List;

public record CategoryPutDTO(
        Long id,
        String name,
        List<Long> productsInCategory
) {
}
