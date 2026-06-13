package br.com.delivery.modules.productSize;

import br.com.delivery.modules.crud.BaseRepository;

public interface ProductSizeRepository extends BaseRepository<ProductSize, Long> {

    boolean existsByProductId(Long idProduct);
}
