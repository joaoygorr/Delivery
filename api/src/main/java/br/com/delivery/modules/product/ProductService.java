package br.com.delivery.modules.product;

import br.com.delivery.modules.product.dtos.ProductDTO;

public interface ProductService {

    void deleteProduct(Long idProduct);

    Product findProduct(Long idProduct);

    ProductDTO createProduct(ProductDTO dto);
}
