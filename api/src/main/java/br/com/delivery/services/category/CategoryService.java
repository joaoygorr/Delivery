package br.com.delivery.services.category;

import br.com.delivery.modules.category.Category;
import br.com.delivery.records.category.CategoryRecord;

public interface CategoryService {

    Category createCategory(CategoryRecord categoryRecord);
}
