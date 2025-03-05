package br.com.delivery.services.category;

import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.category.Category;
import br.com.delivery.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category createCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Category getById(Long id) {
        if (id == null) throw new IllegalArgumentException("o ID não pode ser nulo");
        return this.categoryRepository.findById(id).orElseThrow(() -> new Exception404("Categoria não encontrada"));
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        getById(id);
        this.categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Category updateCategory(Long id, Category entity) {
        Category categoryUpdate = getById(id);
        categoryUpdate.setName(entity.getName());
        return categoryUpdate;
    }
}
