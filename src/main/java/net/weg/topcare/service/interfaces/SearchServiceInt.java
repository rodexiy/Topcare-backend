package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.product.ProductMinimalGetDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchServiceInt {
    Page<ProductMinimalGetDTO> searchProduct(String query, int page, int size, String sorter);
}
