package br.com.delivery.services.product;

import br.com.delivery.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiveImpl {

    private final ProductRepository productRepository;

    public void createProduct() {

    }

}
