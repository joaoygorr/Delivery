package br.com.delivery.modules.category;

public interface CategoryService {

    void deleteCategory(Long idCategory);

    Category findCategory(Long idCategory);
}
