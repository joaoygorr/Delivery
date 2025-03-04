package br.com.delivery.services.category;

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
}
