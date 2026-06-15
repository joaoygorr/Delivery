package br.com.delivery.modules.category;

import br.com.delivery.configuration.exceptions.Exception400;
import br.com.delivery.configuration.exceptions.Exception404;
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
        Category category = findCategory(idCategory);

        if (productRepository.existsByCategoryId(category.getId())) {
            throw new Exception400("Não é possível excluir a categoria porque existem produtos vinculados.");
        }
        categoryRepository.delete(category);
    }

    @Override
    public Category findCategory(Long idCateogry) {
        if (idCateogry == null) {
            throw new Exception400("O ID da categoria é obrigatório.");
        }
        return categoryRepository.findById(idCateogry).orElseThrow(() -> new Exception404("Categoria não encontrada."));
    }
}
