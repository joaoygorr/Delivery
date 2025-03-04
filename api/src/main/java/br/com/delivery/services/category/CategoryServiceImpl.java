package br.com.delivery.services.category;

import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.mapper.CategoryMapper;
import br.com.delivery.records.category.CategoryRecord;
import br.com.delivery.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryRecord categoryRecord) {
        return this.categoryRepository.save(this.categoryMapper.toEntity(categoryRecord));
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

    @Override
    public void deleteCategory(Long id) {
        getById(id);
        this.categoryRepository.deleteById(id);
    }
}
