package com.br.burguer.services.category;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.category.Category;
import com.br.burguer.modules.category.CategoryRepository;
import com.br.burguer.modules.category.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::toDto);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception404("Category with code " + categoryId + " not found!"));
        return CategoryDTO.toDto(category);
    }
}
