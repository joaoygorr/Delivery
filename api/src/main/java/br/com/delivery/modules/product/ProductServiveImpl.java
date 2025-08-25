package br.com.delivery.modules.product;

import br.com.delivery.configuration.exceptions.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiveImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product findByProduct(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new Exception404("Produto não encontrado"));
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

}
