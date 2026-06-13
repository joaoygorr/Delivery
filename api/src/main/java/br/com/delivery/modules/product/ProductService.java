package br.com.delivery.modules.product;

public interface ProductService {

    void deleteProduct(Long idProduct);

    Product findProduct(Long idProduct);
}
