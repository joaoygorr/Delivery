package br.com.delivery.services.category;

import br.com.delivery.modules.category.Category;
import br.com.delivery.records.category.CategoryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category createCategory(Category category);

    Page<Category> getCategories(Pageable pageable);

    Category getById(Long id);

    void deleteCategory(Long id);

    Category updateCategory(Long id, CategoryRecord dto);
}
