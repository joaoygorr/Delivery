package com.br.burguer.services.category;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.Category;
import com.br.burguer.modules.Product;
import com.br.burguer.record.category.CategoryNewDTO;
import com.br.burguer.repositories.CategoryRepository;
import com.br.burguer.record.category.CategoryDTO;
import com.br.burguer.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAll(pageable);
        return categories.map(CategoryDTO::toDto);
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception404("Categoria com o id " + categoryId + " não encontrado!"));
        return CategoryDTO.toDto(category);
    }

    @Override
    public CategoryNewDTO createCategory(CategoryNewDTO categoryNewDTO) {
        Category category = this.categoryRepository.save(CategoryNewDTO.toEntity(categoryNewDTO));
        return CategoryNewDTO.toDto(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            this.categoryRepository.deleteById(id);
        } else {
            throw new Exception404("Categoria com o id " + id + " não encontrado!");
        }
    }
}
