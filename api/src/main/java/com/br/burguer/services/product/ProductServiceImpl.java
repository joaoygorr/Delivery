package com.br.burguer.services.product;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.Product;
import com.br.burguer.repositories.ProductRepository;
import com.br.burguer.record.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = this.productRepository.findAll(pageable);
        return productPage.map(ProductDTO::toDto);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new Exception404("Product with code " + productId + " not found!"));
        return ProductDTO.toDto(product);
    }
}
