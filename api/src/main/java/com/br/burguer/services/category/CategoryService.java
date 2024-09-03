package com.br.burguer.services.category;

import com.br.burguer.modules.category.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDTO> getAllCategories(Pageable pageable);

    CategoryDTO getCategoryById(Long categoryId);
}
