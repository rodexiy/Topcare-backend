package net.weg.topcare.service.interfaces;

import net.weg.topcare.controller.dto.category.CategoryGetDTO;
import net.weg.topcare.controller.dto.category.CategoryPatchDTO;
import net.weg.topcare.controller.dto.category.CategoryPostDTO;
import net.weg.topcare.controller.dto.category.CategoryPutDTO;
import net.weg.topcare.entity.Category;

public interface CategoryServiceInt {
    Category postCategory(CategoryPostDTO dto);
    Category putCategory(CategoryPutDTO dto);

    String deleteCategory(Long id);

    Category patchCategory(CategoryPatchDTO dto, Long id);
    CategoryGetDTO getCategory(Long id);
}
