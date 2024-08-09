package net.weg.topcare.controller.dto.product;

import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.productSpecification.ProductSpecificationPostDTO;
import net.weg.topcare.controller.dto.productVarietions.PVPostDTO;

import java.util.List;

public record ProductPostDTO(
        String name,
        String description,
        List<ProductSpecificationPostDTO> specifications,
        List<CategoryPostDTO> categories,
        List<PVPostDTO> productsVariations,

        Integer discount,
        Double price,
        Integer stock
) {}
