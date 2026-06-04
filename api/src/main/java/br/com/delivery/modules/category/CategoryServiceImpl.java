package br.com.delivery.modules.category;

import br.com.delivery.configuration.exceptions.Exception400;
import br.com.delivery.modules.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void deleteCategory(Long idCategory) {
        if (idCategory == null) {
            throw new Exception400("O ID da categoria é obrigatório.");
        }

        if (productRepository.existsByCategoryId(idCategory)) {
            throw new Exception400("Não é possível excluir a categoria porque existem produtos vinculados.");
        }

        Category category = findByCategory(idCategory);
        categoryRepository.delete(category);
    }

    public Category findByCategory(Long idCateogry) {
        if (idCateogry == null) {
            throw new Exception400("O ID da categoria é obrigatório.");
        }
        return categoryRepository.findById(idCateogry).orElseThrow(() -> new Exception400("Categoria não encontrada."));
    }
}
