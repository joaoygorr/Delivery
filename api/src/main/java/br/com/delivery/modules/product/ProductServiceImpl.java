package br.com.delivery.modules.product;

import br.com.delivery.configuration.exceptions.Exception400;
import br.com.delivery.configuration.exceptions.Exception404;
import br.com.delivery.modules.productSize.ProductSizeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;

    @Transactional
    @Override
    public void deleteProduct(Long idProduct) {
        if (productSizeRepository.existsByProductId(idProduct)) {
            throw new Exception400("Não é possível excluir o produto porque existem tamanhos de produtos vinculados.");
        }
        Product product = findProduct(idProduct);
        productRepository.delete(product);
    }

    @Override
    public Product findProduct(Long idProduct) {
        if (idProduct == null) {
            throw new Exception400("O ID do produto é obrigatório.");
        }
        return productRepository.findById(idProduct).orElseThrow(() -> new Exception404("Produto não encontrado."));
    }
}
