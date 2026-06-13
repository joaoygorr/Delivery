package br.com.delivery.modules.category;

public interface CategoryService {

    void deleteCategory(Long idCategory);

    Category findByCategory(Long idCategory);
}
