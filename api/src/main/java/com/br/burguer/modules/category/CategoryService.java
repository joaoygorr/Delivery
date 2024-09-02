package com.br.burguer.modules.category;

import com.br.burguer.modules.category.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::toDto);
    }
}
