package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.entity.Category;

public interface CategoryServiceInt {
    Category postCategory(CategoryPostDTO dto);
}
