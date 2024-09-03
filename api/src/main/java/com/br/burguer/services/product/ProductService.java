package com.br.burguer.services.product;

import com.br.burguer.exceptions.Exception404;
import com.br.burguer.modules.product.Product;
import com.br.burguer.modules.product.ProductRepository;
import com.br.burguer.modules.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = this.productRepository.findAll(pageable);
        return productPage.map(ProductDTO::toDto);
    }

    public ProductDTO getProductById(Long productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new Exception404("Product with code " + productId + " not found!"));
        return ProductDTO.toDto(product);
    }
}
